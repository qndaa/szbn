package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.dto.AuthorSubscriberReport;
import sbnz.integracija.example.dto.SubscribersReport;
import sbnz.integracija.example.facts.Course;
import sbnz.integracija.example.repository.SubscriberRepository;
import sbnz.integracija.example.service.ReportService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final KieContainer kieContainer;
    private final SubscriberRepository subscriberRepository;

    @Autowired
    public ReportServiceImpl(KieContainer kieContainer, SubscriberRepository subscriberRepository) {
        this.kieContainer = kieContainer;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Collection<AuthorSubscriberReport> finishedCoursesFromAuthor(UUID authorId) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        kieSession.getAgenda().getAgendaGroup("report").setFocus();
        kieSession.setGlobal("authorId", authorId);
        return getAuthorSubscriberReports(kieSession);
    }

    @Override
    public Collection<AuthorSubscriberReport> getEnrolledSubscribers(UUID courseId) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        kieSession.getAgenda().getAgendaGroup("soft-report").setFocus();
        kieSession.setGlobal("_courseId", courseId);
        return getAuthorSubscriberReports(kieSession);
    }

    @Override
    public Collection<AuthorSubscriberReport> getAdvancedSubscribers(String area) {
        KieSession kieSession = kieContainer.newKieSession("cepKsession");
        kieSession.getAgenda().getAgendaGroup("admin-report").setFocus();
        kieSession.setGlobal("area", area);
        return getAuthorSubscriberReports(kieSession);
    }

    private Collection<AuthorSubscriberReport> getAuthorSubscriberReports(KieSession kieSession) {
        SubscribersReport report = new SubscribersReport();
        kieSession.insert(report);
        subscriberRepository.findAll().forEach(kieSession::insert);
        kieSession.fireAllRules();
        if (report.getSubscribers() == null)
            report.setSubscribers(new ArrayList<>());
        Collection<AuthorSubscriberReport> subsReport =  report.getSubscribers().stream().map(s -> new AuthorSubscriberReport(s.getName(), s.getSurname(), s.getUsername()))
                .collect(Collectors.toList());
        kieSession.dispose();
        return subsReport;
    }
}

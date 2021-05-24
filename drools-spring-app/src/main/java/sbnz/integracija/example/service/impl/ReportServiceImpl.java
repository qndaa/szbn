package sbnz.integracija.example.service.impl;

import org.kie.api.runtime.KieSession;
import sbnz.integracija.example.facts.Subscriber;
import sbnz.integracija.example.service.ReportService;

import java.util.Collection;
import java.util.UUID;

public class ReportServiceImpl implements ReportService {
    KieSession session;

    @Override
    public Collection<Subscriber> finishedCoursesFromAuthor(UUID authorId) {

        return null;
    }
}

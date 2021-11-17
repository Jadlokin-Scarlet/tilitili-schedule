package club.tilitili.schedule.service;

import club.tilitili.schedule.dao.TilitiliJobDAO;
import club.tilitili.schedule.entity.TilitiliJob;
import club.tilitili.schedule.entity.query.TilitiliJobQuery;
import club.tilitili.schedule.entity.view.ResourceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TilitiliJobService {
    private final TilitiliJobDAO tilitiliJobDAO;

    @Autowired
    public TilitiliJobService(TilitiliJobDAO tilitiliJobDAO) {
        this.tilitiliJobDAO = tilitiliJobDAO;
    }

    public List<ResourceView> getJobNameResource() {
        List<TilitiliJob> jobList = tilitiliJobDAO.getTilitiliJobByCondition(new TilitiliJobQuery());
        return jobList.stream().map(job->new ResourceView(job.getName(), job.getTitle().isEmpty()? job.getName(): job.getTitle())).collect(Collectors.toList());
    }
}

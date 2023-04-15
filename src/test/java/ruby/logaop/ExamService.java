package ruby.logaop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@LogTrace
public class ExamService {

    private final ExamRepository examRepository;

    public void request(String id) {
        examRepository.save(id);
    }
}

package ott.j4jg_be.application.port.out.collection;

import java.util.List;

public interface JobInfoPort {
    List<String> getCompanyNames(int page, int size);
}

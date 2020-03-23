package softuni.workshop.service.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.service.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void importCompanies() {
        //TODO seed in database
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() {
        //TODO read xmlFile
        return null;
    }
}

package softuni.workshop.service.services;

public interface CompanyService {

    void importCompanies();

    boolean areImported();

    String readCompaniesXmlFile();
}

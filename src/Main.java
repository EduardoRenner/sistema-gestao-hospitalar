import model.Convenio;
import model.Paciente;
import model.PacienteConvenio;
import model.PacienteParticular;
import service.HospitalService;

public class Main {
    static void main(String[] args) {
        HospitalService hospitalService = new HospitalService();
        hospitalService.menu();
    }
}

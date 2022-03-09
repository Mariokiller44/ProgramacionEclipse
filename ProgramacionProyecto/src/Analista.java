import java.time.LocalDate;

public class Analista extends Empleado {

	public Analista(int numEmpleado, String apellido, LocalDate fechaAlta, double salario,Departamento departamento) {
		super(numEmpleado, apellido, fechaAlta, salario, departamento);
	}
	public Analista() {
		
	}
	@Override
	public void saludar() {
	System.out.println("Hola, les habla un Analista");
	System.out.println("Mi nombre es: "+getNombre());
	}
	
	@Override
	public String toString() {
		return "\n"+"Analista: "+"Numero de empleado: " + getNumEmpleado() + ", Nombre: " + getNombre() + ", Alta: " + getFechaAlta() + ", Salario mensual: "
				+ getSalario()+"€" + ", Salario anualmente: "+"€";
	}
	@Override
	public double salarioAnual() {
		// TODO Auto-generated method stub
		return 0;
	}
}

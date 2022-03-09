import java.time.LocalDate;

public class Directivo extends Empleado{

	private double comision;
	
	public Directivo(int numEmpleado, String apellido, LocalDate fechaAlta, double salario,double comision,Departamento departamento) {
		super(numEmpleado, apellido, fechaAlta, salario, departamento);
		this.comision=comision;
	}

	@Override
	public void saludar() {
	System.out.println("Hola, les habla un Directivo");
	System.out.println("Mi nombre es: "+getNombre());
	}
	
	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	@Override
	public String toString() {
		return "\n"+"Directivo: "+"Numero" + getNumEmpleado() + ", Nombre: "
				+ getNombre() + ", Alta: " + getFechaAlta() +", Salario mensual: " + getSalario()
				+"€"+ ", Salario anual: " +"\n";
	}

	@Override
	public double salarioAnual() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

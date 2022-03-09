import java.time.LocalDate;

public class Empleado {

	private int TAM=5;
	private int numEmpleado;
	private String apellido;
	private LocalDate fechaAlta;
	private double salario;
	private double comision;
	private Departamento departamento;
	
	public Empleado() {
		
	}
	public Empleado(int numEmpleado, String apellido, double salario, LocalDate fechaAlta, double comision, Departamento departamento) {
		this.numEmpleado = numEmpleado;
		this.apellido = apellido;
		this.salario=salario;
		this.fechaAlta = fechaAlta;
		this.comision=comision;
		this.departamento=departamento;
	}

	public Empleado(int numEmpleado, String apellido, LocalDate fechaAlta, double salario, Departamento departamento) {
		this.numEmpleado = numEmpleado;
		this.apellido = apellido;
		this.fechaAlta = fechaAlta;
		this.salario = salario;
		this.departamento=departamento;
	}
	public void mostrarEmpleado() {
		System.out.println();
	}
	public int getTAM() {
		return TAM;
	}
	public void setTAM(int tAM) {
		TAM = tAM;
	}
	public void saludar() {
		System.out.println("Hola, les habla un Empleado");
	}

	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public String getNombre() {
		return apellido;
	}

	public void setNombre(String nombre) {
		this.apellido = nombre;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
//	public abstract double salarioAnual(); 
//	public String toString() {
//		return "\t"+"Apellido: "+ apellido + "-Numero empleado: " + numEmpleado + "-Fecha alta:" + fechaAlta + "Salario: "
//				+ salario +"\n";
//	}
//	
}

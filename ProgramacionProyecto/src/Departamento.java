import java.time.LocalDate;
import java.util.Arrays;

public class Departamento {

	private int TAM=5;
	private int dept_no;
	private String dnombre;
	private String localizacion;
	private Empleado[] Empleados;
	private int numeroEmpleados;
	private int posicionInserciones;
	
	public Departamento() {
		
	}
	
	public Departamento(int dept_no,String dnombre,String localizacion) {
		super();
		this.dept_no=dept_no;
		this.dnombre=dnombre;
		this.localizacion=localizacion;
		this.Empleados= new Empleado[TAM];
		this.Empleados[0]= new Analista(10,"Maria",LocalDate.of(1969, 3, 12),200,null);
		this.Empleados[1]= new Directivo(20, "Luis",LocalDate.of(1980, 9, 10), 200,dept_no, null);
	}
	
	public Departamento(int dept_no,String dnombre,String localizacion,Empleado[] Empleados) {
		this.dept_no=dept_no;
		this.dnombre=dnombre;
		this.localizacion=localizacion;
		this.Empleados=Empleados;
	}
	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public int getTAM() {
		return TAM;
	}

	public void setTAM(int tAM) {
		TAM = tAM;
	}

	public int getPosicionInserciones() {
		return posicionInserciones;
	}

	public void setPosicionInserciones(int posicionInserciones) {
		this.posicionInserciones = posicionInserciones;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Empleado[] getEmpleados() {
		return Empleados;
	}

	public void setEmpleados(Empleado[] empleados) {
		Empleados = empleados;
	}
	


	
//	public String toString() {
//		return "Nombre: " + dnombre +  "-localizacion" + localizacion + "- Numero departamento: " + dept_no
//				+ "\n"+"\t"+Arrays.toString(Empleados)+"\n";
//		
//	}
	
}

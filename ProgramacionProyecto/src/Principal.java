import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Principal {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int opcion=0;
	static boolean salirMenuPrincipal=false;
	static int opcionesSubmenu=0;
	static boolean salidaMenuDepartamentos=false;
	static int TAM=4;
	static int posicionDep=TAM-1;
	static Departamento[] departamentos = new Departamento[TAM];
	static Empleado[] empleados= new Empleado[TAM];
	static int opcionesSubmenuDepart;
	
	
	
	
	public static void main(String[] args) {
		
		cargaAutomaticaPorComposicion();
//	    cargaAutomaticaPorAgregacion();
//		modificacionNumDep(7);
//		mostrarTodosDepartamentos();
//		System.out.println(borrarDep(7));
//		existeDepartamento(15);
//		menu();

//		System.out.println(empleadosVacio(departamentos[1]));
//		mostrarEmpleados(departamentos[0]);
		System.out.println(borrarEmpleado(departamentos[0], 10));
	}
	public static int borrarEmpleado(Departamento d, int numeroEmpleado) {
		int control=0;//No existe el empleado en ningun departamento
		int posicion=0;
		boolean salidaMetodo=false;
		while(existeEmpleado(numeroEmpleado)==0) {
			for(int i=0;i<d.getEmpleados().length;i++) {
				if(d.getEmpleados()[i]!=null && salidaMetodo==false) {
					if(numeroEmpleado==d.getEmpleados()[i].getNumEmpleado()) {
						d.getEmpleados()[i]=null;
						control=1;//borrado OK
						break;
					}
					else {
						posicion++;
					}
					if(posicion==d.getEmpleados().length) {
						control=-1;//No existe el empleado en ese departamento
						break;
					}
				}
			}
			break;
		}
		return control;
	}
	public static int existeEmpleado(int numero_Empleado) {
		boolean existe=false;
		int contador=0;
		int r=0;
		do 
		{
				if(departamentos[contador]!=null && departamentos[contador].getEmpleados()[r].getNumEmpleado()==numero_Empleado) 
				{
					existe=true;
				}
				else contador++;
				r++;
		}while(!existe && contador< TAM);
		
		if(existe) return contador;
		else return  -1;
	}
	public static void insertarEmpleadoInteractivo()throws IOException {
		System.out.println("Seleccione en que departamento quiere insertar un empleado");
		int deptInsertar = Integer.parseInt(br.readLine());
		int posDeptInsertar = existeDepartamento(deptInsertar);
		if(posDeptInsertar == -1) {
			System.out.println("El departamento elegido no existe.");
		}
		else {
			if(empleadosLleno(departamentos[posDeptInsertar])) {
				System.out.println("No caben mas empleados en este departamento.");
			}
			else {
				Empleado emp;
				System.out.println("Indique el numero del empleado: ");
				int numEmp= Integer.parseInt(br.readLine());
				if(existeEmpleado(departamentos[posDeptInsertar]) != -1) {
					System.out.println("Ese empleado ya existe en el departamento.");
				}
				else {
					System.out.println("Que quiere insertar:\n1 - Un analista. \n2 - Un Director.");
					int tipoEmpleado=Integer.parseInt(br.readLine());
					System.out.println("Indique el apellido del empleado: ");
					String apellidoEmp = br.readLine();
					System.out.println("Indique la fecha de alta del empleado: \nFormato aaaa-mm-dd");
					LocalDate fechaAltaEmp=LocalDate.parse(br.readLine());
					System.out.println("Indique el salario del empleado");
					double salarioEmp = Integer.parseInt(br.readLine());
					if(tipoEmpleado == 1) {
						emp=new Analista(numEmp, apellidoEmp, fechaAltaEmp, salarioEmp, departamentos[0]);
					}
					else {
						System.out.println("Indique la comision del empleado: ");
						double comision = Integer.parseInt(br.readLine());
						emp = new Directivo(numEmp, apellidoEmp, fechaAltaEmp, salarioEmp, comision, departamentos[0]);
					}
				}
			}
		}
	}
	public static int insertarEmpleado(Empleado e,Departamento d) {
		int salida=-1;
		int posicion=0;
		if(!empleadosLleno(d)) {
			while(salida == -1 && posicion != d.getTAM()) {
				if(existeDepartamento(d,e) !=e) {
					d.getEmpleados()[d.getPosicionInserciones()] = e;
					System.out.println(e.mostrarEmpleado());
					salida = 1;
				}
				else {
					posicion++;
					salida=0;
				}
			}
		}
		return salida;
	}
	public static void mostrarEmpleados(Departamento d) {
		if(!empleadosVacio(d))
		for(int i=0;i<TAM;i++) { 
			if(d.getEmpleados()[i]!=null) {
				System.out.printf(d.getEmpleados()[i].getApellido()+" ");
				System.out.print(d.getEmpleados()[i].getComision()+" ");
				System.out.print(d.getEmpleados()[i].getNumEmpleado()+" ");
				System.out.print(d.getEmpleados()[i].getSalario()+" ");
			}
			System.out.println();
		}
	}
	public static boolean empleadosVacio(Departamento d) {
		return(d.getEmpleados()==null);
	}
	public static boolean empleadosLleno(Departamento d) {
		return(d.getEmpleados().length==TAM);
	}
	public static int modificarDepartamento(int num_dep) {
		if(existeDepartamento(num_dep)!=0) {
			pintarSubmenuDepart();
			if(elegirModificacion()==1) {
				modificacionNumDep(num_dep);
				return 1;
			}else if(elegirModificacion()==2){
				modificacionNombreDep(num_dep);
				return 2;
			}else if(elegirModificacion()==3) {
				modificacionLocalizacionDep(num_dep);
				return 3;
			}
		}
		return -1;
	}
	public static void pintarSubmenuDepart() {
		System.out.println("¿Que desea modificar del departamento?");
		System.out.printf("1: Modificar numero del departamento");
		System.out.printf("2: Modificar nombre del departamento");
		System.out.printf("3: Modificar localizacion del departamento");
		System.out.printf("4: Salir");
		try{
			opcionesSubmenuDepart= Integer.parseInt(br.readLine());
		}catch(IOException ioe) {
			System.out.println("Eso no es un dato");
		}catch(NumberFormatException nfe) {
			System.out.println("Eso no es un numero");
		}		
	}
	public static int elegirModificacion() {
		int opcionesMod=0;
		switch(opcionesSubmenuDepart) {
		case 1:
			opcionesMod=1;
		case 2:
			opcionesMod=2;
		case 3:
			opcionesMod=3;
		case 4:
			opcionesMod=4;
		}
		return opcionesMod;
	}
	public static int modificacionNumDep(int nuevo_dep) {
		System.out.println("Introduzca numero de departamento nuevo");
		int controlarModificacion=0;
		int controlarErrores=0;
		int departamento_num;
		try {
			departamento_num= Integer.parseInt(br.readLine());
		}catch(IOException ioe) {
			System.out.println("Eso no es un dato");
			return -1;
		}catch(NumberFormatException nfe) {
			System.out.println("Eso no es un numero");
			return -2;
		}
		for(int j=0;j<departamentos.length;j++) {
			if(departamentos[j]==null) {
				System.out.println();
				controlarErrores=2;
			}else {
					if(departamentos[j].getDept_no()==nuevo_dep) {
						departamentos[j].setDept_no(departamento_num);
						controlarModificacion=0;
					}
			}		
		}
		return controlarModificacion;
	}
	public static int modificacionNombreDep(int nuevo_dep) {
		System.out.println("Introduzca nombre de departamento nuevo");
		int controlarModificacion=0;
		int controlarErrores=0;
		String departamento_nombre;
		try {
			departamento_nombre= br.readLine();
		}catch(IOException ioe) {
			System.out.println("Eso no es un dato");
			return -1;
		}
		for(int j=0;j<departamentos.length;j++) {
			if(departamentos[j]==null) {
				System.out.println();
				controlarErrores=2;
			}else {
					if(departamentos[j].getDept_no()==nuevo_dep) {
						departamentos[j].setDnombre(departamento_nombre);
						controlarModificacion=0;
					}
			}		
		}
		return controlarModificacion;
	}
	public static int modificacionLocalizacionDep(int nuevo_dep) {
		System.out.println("Introduzca nueva localizacion del departamento");
		int controlarModificacion=0;
		int controlarErrores=0;
		String departamento_localizacion;
		try {
			departamento_localizacion= br.readLine();
		}catch(IOException ioe) {
			System.out.println("Eso no es un dato");
			return -1;
		}
		for(int j=0;j<departamentos.length;j++) {
			if(departamentos[j]==null) {
				System.out.println();
				controlarErrores=2;
			}else {
					if(departamentos[j].getDept_no()==nuevo_dep) {
						departamentos[j].setLocalizacion(departamento_localizacion);
						controlarModificacion=0;
					}
			}		
		}
		return controlarModificacion;
	}
	public static void cargaAutomaticaPorComposicion() {
		departamentos[0]= new Departamento(15, "Ventas", "Barcelona");
		departamentos[1]= new Departamento(7, "Innovacion", "Helechosa de los Montes");
	}
	public static void cargaAutomaticaPorAgregacion() {
		Empleado[] empleados1= new Empleado[TAM];
		Empleado[] empleados2= new Empleado[TAM];
		departamentos[0]=new Departamento(15,"Ventas", "Barcelona", null);
		empleados1[0]= new Empleado(10, "Gomez ", 3000, LocalDate.of(1969, 3, 12),200, departamentos[0]);
		empleados1[1]= new Empleado(20, "Gonzalez ", 2600, LocalDate.of(1980, 9, 10), 200, departamentos[0]);
		departamentos[0].setEmpleados(empleados1);
		
		empleados2[0]= new Empleado(30, "Martin ", 5000, LocalDate.of(2001, 6, 18),400, departamentos[1]);
		empleados2[1]= new Empleado(40, "Perez ", 6000, LocalDate.of(1993, 4, 12),100, departamentos[1]);
		departamentos[1]=new Departamento(7, "Innovacion ", "Helechosa de los Montes ", empleados2);
		departamentos[1].setEmpleados(empleados2);
			
	}
	public static int borrarDep(int num_Dep) {/*En este metodo devuelve el valor 0 si el departamento no existe, 1 si el departamento existe y no esta vacio y 
	2 si existe y esta vacio*/
		int control=0;//variable de control o retorno
		if(existeDepartamento(num_Dep)!=0) {//bucle if que nos cumprueba si el departamento existe o no
			for(int i=0;i<departamentos.length;i++){//bucle que recorre el departamento para despues comprobar si esta vacio o no
				if(departamentosVacio(i)==false) {
					control=2;
					break;
				}
				else {
					control=1;
				}
			}
		}else {
			control=-1;
		}
		return control;
	}
	public static boolean departamentosVacio(int numero_departamento) {
		boolean controlarDep=false;
		if(departamentos[numero_departamento]==null) {
			controlarDep=true;
		}
		return controlarDep;
	}
	public static int buscaHueco() {
		boolean encontrado=false;
		int contador=0;
		while(!encontrado && contador<TAM) {
			if(departamentos[contador]==null) {
				encontrado=true;
			}
			else contador++;
		}
		return contador;
	}
	public static int existeDepartamento(int numero_dep) {
		boolean existe=false;
		int contador=0;
		if(departamentos[numero_dep]!=null) {
			do {
				if(departamentos[contador]!=null && departamentos[contador].getDept_no()==numero_dep) {
					existe=true;
				}
				else contador++;
			}while(!existe && contador< departamentos.length);
		}else return -2;
		if(existe) return contador;
		else return  -1;
	}
	public static void mostrarTodosDepartamentos() {
		int contadorDep=0;
		int contadorEmp=0;
		
		for(int i=0;i<departamentos.length;i++) {
			if(departamentos[i]!=null) {
				System.out.print("Nombre: "+departamentos[i].getDnombre());
				System.out.printf("- Localizacion: "+departamentos[i].getLocalizacion());
				System.out.printf("- Numero departamento: "+departamentos[i].getDept_no());
				System.out.println(" Empleados:");
				for (int j=0;j<empleados.length;j++) {
					if(departamentos[i].getEmpleados()[j]!=null) {
						System.out.printf("- Apellido: "+departamentos[i].getEmpleados()[j].getApellido());
						System.out.printf("- Numero Empleado: "+departamentos[i].getEmpleados()[j].getNumEmpleado());
						System.out.printf("- Fecha de alta: "+departamentos[i].getEmpleados()[j].getFechaAlta());
						System.out.println("- Salario: "+departamentos[i].getEmpleados()[j].getSalario());
					}else {
						System.out.println("Este departamento no tiene empleados");
						contadorEmp++;
					}
				}
			}else {
				System.out.println("Este departamento esta vacio");
				contadorDep++;
			}
		}
		System.out.println("Hay "+contadorDep+" departamentos vacios y "+contadorEmp+" empleados vacios");
		
	}
	public static void menu() {
		do {
			System.out.println("Bienvenido al menu principal. \f Por favor elija que opcion quiere usar:");
			System.out.println("1: Departamentos \n 2: Empleados \n 3: Salir del programa");
			try {
				opcion = Integer.parseInt(br.readLine());
			}catch(IOException ioe) {
				System.out.println("Eso no es un dato");
			}catch(NumberFormatException nfe) {
				System.out.println("Eso no es un numero");
			}
			sacarOpciones(opcion);
		}while(!salirMenuPrincipal);
	}
	public static void sacarOpciones(int opciones) {
		switch(opciones) {
			case 1:
				menuDepartamentos();
				break;
			case 2:
//				menuEmpleados();
				break;
			case 3:
				System.out.println("Saliendo del programa");
				salirMenuPrincipal=true;
				break;
			default:
				System.out.println("Solo hay tres opciones");
		}
	}
	public static void menuDepartamentos() {
		do {
			System.out.println("Que desea hacer con los Departamentos"+"\n");
			System.out.println("1: Mostrar todos los departamentos");
			System.out.println("2: Mostrar un unico departamento");
			System.out.println("3: Aniadir departamentos");
			System.out.println("4: Borrar departamentos");
			System.out.println("5: Volver al menu principal");
			try {
				opcionesSubmenu =Integer.parseInt(br.readLine());
			}catch(IOException ioe) {
				System.out.println("Eso no es un dato");
				salidaMenuDepartamentos=false;
			}catch(NumberFormatException nfe) {
				System.out.println("Eso no es un numero");
				salidaMenuDepartamentos=false;
			}
			sacarOpciones2(opcionesSubmenu);
		}while(!salidaMenuDepartamentos);
	}
	public static void sacarOpciones2(int opcion) {
		switch(opcion) {
			case 1:
				mostrarDepartamentos();
				break;
			case 2:
				muestraDepartamento();
				break;
			case 3:
				aniadeDepartamento();
				break;
			case 4:
				borrarDepartamento();
				break;
			case 5:
				menu();
				salidaMenuDepartamentos=true;
				break;
			default: 
				System.out.println("Solo existen 4 opciones");
		}
	}
	public static void mostrarDepartamentos() {
		System.out.println("Esta en la opcion de mostrar departamentos, de momento no existe una opcion que pueda salir, asi que volviendo al menu");
		menuDepartamentos();
	}
	public static void muestraDepartamento() {
		System.out.println("Esta es la opcion de muestra de un departamento. Volviendo al menu de departamentos");
		menuDepartamentos();
	}
	public static void aniadeDepartamento() {
		System.out.println("Esta es la opcion de aniadir departamento. Volviendo al menu de departamentos");
		menuDepartamentos();
	}
	public static void borrarDepartamento() {
		System.out.println("Esta es la opcion de borrar departamento. Volviendo al menu de departamentos");
		menuDepartamentos(); 
	}

}

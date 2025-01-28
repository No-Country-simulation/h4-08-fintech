# **IUPI** 💰  
**Ahorro e inversiones para un futuro financiero seguro**  

---

## **Descripción del Proyecto** 🚀  
**IUPI** es una aplicación financiera diseñada para empoderar a los usuarios en la gestión y crecimiento de su patrimonio. En un contexto económico complejo y cambiante como el de Argentina, IUPI brinda herramientas accesibles y personalizadas para facilitar el ahorro y la inversión, adaptándose a personas de diferentes edades y niveles de experiencia financiera.  

### **Misión** 🌍  
Fomentar una cultura de ahorro e inversión responsable, proporcionando herramientas que permitan a los usuarios crecer financieramente, cumplir sus objetivos y adaptarse a los desafíos de un entorno económico incierto.  

### **Problema que Resuelve** 🎯  
- Falta de conocimiento sobre cómo hacer crecer el dinero más allá de opciones básicas.  
- Desconfianza en el sistema financiero y en la capacidad para gestionar inversiones.  
- Dificultad para visualizar metas financieras a largo plazo en un contexto económico volátil.  

### **Usuario Ideal** 👤  
- **Edad:** 18 a 50 años (inclusivo para otros rangos).  
- **Nivel educativo:** Desde personas sin educación financiera formal hasta aquellas con conocimientos básicos.  
- **Situación financiera:** Ingresos regulares y ahorros iniciales (aunque sean modestos).  
- **Objetivos:** Incrementar su patrimonio, comprar bienes, planificar su retiro o proyectos a largo plazo.  

---

## **Funcionalidades Principales** ✨  

### 1. **Autenticación y Onboarding Personalizado**  
- Registro mediante Google, Apple ID o correo electrónico.  
- Onboarding tipo wizard para identificar el perfil financiero del usuario (conocimiento financiero, objetivos, preferencias de riesgo, ingresos y gastos).  

### 2. **Dashboard Personalizado y Radiografía Financiera**  
- Resumen de ingresos, gastos y capacidad de ahorro mensual.  
- Progreso visual hacia objetivos financieros con elementos de gamificación.  
- Accesos directos a inversiones recomendadas.  

### 3. **Gestión de Inversiones Personalizadas**  
- Exploración de instrumentos financieros (CEDEARs, fondos comunes de inversión, etc.).  
- Recomendaciones basadas en el perfil de riesgo y objetivos del usuario.  
- Visualización de rendimientos en pesos y dólares.  

### 4. **Comunidad y Foro de Noticias**  
- Noticias financieras personalizadas mediante APIs.  
- Foro interactivo para compartir opiniones y estrategias.  
- "Tip del día" o término financiero educativo.  

---

## **Tecnologías Utilizadas** 🛠️  

### **Backend**  
- **Lenguaje:** Java  
- **Frameworks:** Spring Boot
- **APIs:** Integración con APIs de datos bancarios y de mercado.  

### **Frontend**  
- **Lenguaje:** JavaScript  
- **Framework:** React  
- **Librerías:** Redux, Axios, Chart.js

### **Base de Datos**  
- PostgreSQL    

### **Otras Herramientas**  
- Git  
- Swagger (Documentación de APIs)

---

## **Instalación y Ejecución Local** ⚙️  

### Requisitos Previos  
- Java JDK 17  
- Node.js (v16 o superior)  
- npm (v8 o superior)  
- PostgreSQL (v14 o superior)   

### Pasos para Ejecutar el Proyecto  

1. **Clona el repositorio:**  
   ```bash
   git clone https://github.com/No-Country-simulation/h4-08-fintech.git
   cd h4-08-fintech
   ```
2. **Configura la base de datos**:  
- Crea una base de datos en PostgreSQL llamada `iupi_db`.  
- Configura las credenciales en el archivo `application.properties` del backend.  

3. **Instala y ejecuta el backend**:  
  ```bash
  cd backend
  mvn clean install
  mvn spring-boot:run
  ```
4. **Instala y ejecuta el frontend**:
  ```bash
  cd ../frontend
  npm install
  npm start
  ```
5. **Accede a la aplicación**:
  Abre tu navegador y visita:
  ```bash
  http://localhost:3000
  ```
###Documentación de la API:
  Accede a la documentación de la API en:
  ```bash
  http://localhost:8080/swagger-ui.html
  ```

# Colaboradores 👥

| Nombre      | Apellido   | Rol         | LinkedIn  | GitHub   |
|-------------|------------|-------------|-----------|----------|
| Nicolas     | Lozano     | Backend     | [LinkedIn](#) | [GitHub](#) |
| Chenhao     | Hu         | Backend     | [LinkedIn](#) | [GitHub](#) |
| Juan José   | Giraldo    | Full-Stack  | [LinkedIn](#) | [GitHub](#) |
| Fernanda    | Sivila     | Frontend    | [LinkedIn](https://www.linkedin.com/in/fernanda-sivila/) | [GitHub](https://github.com/fernandasivila) |
| Agustina    | Mena       | Frontend    | [LinkedIn](#) | [GitHub](#) |
| Ivan        | Belasich   | Frontend    | [LinkedIn](#) | [GitHub](#) |
| Lucas       | Eroles     | UX/UI       | [LinkedIn](#) | [GitHub](#) |
| Valentina   | Junca      | UX/UI       | [LinkedIn](#) | [GitHub](#) |
| Matías      | Quirico    | Frontend    | [LinkedIn](#) | [GitHub](#) |

---

## Enlaces Importantes 🔗

- **Repositorio del Proyecto**: [GitHub - IUPI](#)
- **Documentación de la API**: [Swagger UI](#)
- **Tablero de Trabajo (Notion)**: [Enlace al Tablero](#)
- **Diseños en Figma**: [Figma - IUPI](#)

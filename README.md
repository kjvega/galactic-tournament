# Galactic Tournament

Aplicación Java Spring Boot 3.5.7 + PostgreSQL 17 que simula batallas entre especies galácticas.  
El proyecto está dockerizado para facilitar su ejecución sin configuraciones locales.

---

## Tecnologías principales

- Java 17  
- Spring Boot 3.5.7  
- PostgreSQL 17  
- Docker / Docker Compose  
- Maven 3.9.9  
- JUnit 5 + Mockito  

---

---

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalados:

- Docker Desktop  
- Git (opcional, para clonar el repositorio)

Verifica que Docker esté activo:

```bash
docker --version
docker compose version
```

---

## 1. Clonar el proyecto

```bash
git clone https://github.com/kjvega/galactic-tournament.git
cd galactic-tournament
```

---

## 2. Construir y ejecutar con Docker Compose

Ejecuta el siguiente comando en la raíz del proyecto:

```bash
docker compose up --build
```

Esto hará lo siguiente:

1. Levanta PostgreSQL 17 en el puerto 5432  
2. Compila y ejecuta la aplicación Spring Boot en el puerto 8081  
3. Crea automáticamente las tablas `species` y `battle_results` en la base de datos `tournament_db`

---

## 3. Endpoints disponibles

Una vez corra el contenedor, la API estará disponible en:

```
http://localhost:8081
```

### Endpoints principales

| Método | Endpoint               | Descripción                       |
|--------|------------------------|-----------------------------------|
| POST   | /api/species           | Registrar una nueva especie       |
| GET    | /api/species           | Listar todas las especies         |
| GET    | /api/species/ranking   | Obtener ranking de victorias      |
| POST   | /api/species/fight     | Simular una batalla entre especies|

---

## 4. Ejemplo de uso en Postman

### Registrar especie  
**POST** `http://localhost:8081/api/species`

```json
{
  "name": "Zorgon",
  "powerLevel": 85,
  "specialSkill": "Fire Blast"
}
```

### Listar especies  
**GET** `http://localhost:8081/api/species`

---

## 5. Configuración de base de datos

Por defecto, el contenedor PostgreSQL usa:

| Variable              | Valor         |
|------------------------|---------------|
| POSTGRES_USER          | postgres      |
| POSTGRES_PASSWORD      | postgres      |
| POSTGRES_DB            | tournament_db |
| Puerto                 | 5432          |

Si deseas modificar estas credenciales, puedes hacerlo directamente en el archivo `docker-compose.yml`.

---

## 6. Ejecutar pruebas

Si deseas ejecutar las pruebas unitarias y de servicio localmente:

```bash
./mvnw test
```

---

## 7. Detener y limpiar los contenedores

```bash
docker compose down
```

Si deseas borrar también los volúmenes y datos persistidos:

```bash
docker compose down -v
```


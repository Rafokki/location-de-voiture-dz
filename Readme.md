**docker-compose -f docker-compose.yml up -d**
**docker-compose -f docker-compose.yml down**
docker pull postgres
docker run -itd -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -p 5432:5432 -v /var/lib/postgresql/data --name postgresql postgres
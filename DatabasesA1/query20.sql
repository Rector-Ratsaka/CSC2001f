select customerNumber from payments group by customerNumber order by count(*) asc limit 1
select employeeNumber from employees group by reportsTo order by count(distinct reportsTo) desc limit 1


select customerNumber, offices.city from customers inner join employees on customers.salesRepEmployeeNumber = employees.employeeNumber inner join offices on employees.officeCode = offices.officeCode


select orderNumber, coalesce(shippedDate,requiredDate,orderDate) as day from orders
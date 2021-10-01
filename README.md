# Introduction
The process in a restaurant is usually the same. Customers get seated, are handed the menu, make an order, consume their food and pay the bill. The idea if to convert the above process to a more automated experience. As part of a group project I developed a proof of concept of concept of what this might look like. 

[Gif of ordering food here]

Customers will be able to scan a QR Code which will open a menu and link their device to their table. They're able to browse the menu and select their desired items. Once an order has been made it shows up in the kitchen. Ingredients are automatically subtracted from the stock, which can be manually adjusted when necessary. 
What would the new preferred situation look like?

The application is meant to lighten the workload of the staff which will in turn make it possible to speed up the ordering process. It will also be a lot easier to make changes to the menu and keep track of the stock, which is a great benefit for kitchen. 

## Implementation
We decided to split the app into three portals: the [Menu](https://github.com/Desoxyr/Mediaan-Digital-Menu/tree/master/Menu%20Service), the [Kitchen](https://github.com/Desoxyr/Mediaan-Digital-Menu/tree/master/Kitchen%20View) and the [Management](https://github.com/Desoxyr/Mediaan-Digital-Menu/tree/master/Management%20View). This allowed us to split the responsibility of each portal without having to focus on security and role management.

## Documentation

If you're interested in checking out more of the documentation of the project I highly recommend you check out the [Analysis] and the [Architecture & Technical Choices].
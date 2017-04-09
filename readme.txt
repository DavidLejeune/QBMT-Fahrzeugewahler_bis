Doelstelling :
--------------

Ontwikkel een Android app die het volgende doet :

- Gebruik Android studio

- Dbase op server (technologie & hosting naar keuze) bevat :
+ Paar auto's
+ Per auto een prijs
+ Per auto een paar opties en elk van die opties hebben een prijs

- Op Android :
+ Toon de auto's + presenteer verschillende opties
+ Laat een klant een auto selecteren + opties aan/af vinken
+ Afh van keuze de totale prijs berekenen/tonen
+ Toon buy button
+ Indien gedrukt op de buy button -> feliceer de klant bij zijn aankoop



Uitwerking :
------------

- Database op server

  * ftp server gebruikt , dus geen sql

  * 'databases' zijn csv bestanden en zien er als volgt uit :

          car_types :
                      id,name,desciptor,image,price
                      1,Station wagon,station_wagon,car0,4000
                      2,Bollo car,bollo_car,car1,3500
                      3,Pickup truck,pickup_truck,car2,3000
                      4,Race car,race_car,car3,5000

          options :
                      id,name,desciptor,image,price
                      1,Logo,logo,option0,375
                      2,Police lights,police_lights,option1,525

          options_car0 :
                      id,name,desciptor,available
                      1,Logo,option0,true
                      2,Police lights,option1,true

          options_car1 :
                      id,name,desciptor,available
                      1,Logo,option0,true
                      2,Police lights,option1,false

          options_car2 :
                      id,name,desciptor,available
                      1,Logo,option0,false
                      2,Police lights,option1,true

          options_car3 :
                      id,name,desciptor,available
                      1,Logo,option0,false
                      2,Police lights,option1,false

    * Deze data files worden binnen getrokken via een ftp connectie (async)
      Later wordt deze data gebruikt om gegevens te queryen

    * Bij het beeindigen van de app worden alle data files gewist

- Op Android :

    * Basis thema : wit
      Niet wat ikzelf graag zie (dark themes ftw) , maar draagt voorkeur van de
      klant.

    * Gebruiker kan een basis profiel aanmaken

    * Indien nog geen auto aangemaakt is kan hij enkel één aanmaken

    * Auto creatie is zeer basic, slechts 4 auto's en 2 keuzes
      Alle data wordt on the fly gequeried in de datafile (naam, prijs etc ..)
      Werkwijze zou zichzelf moeten uitwijzen.
      Opties van de auto's zijn beschikbaar volgens de 'database' files

    * Auto aankopen levert een eenvoudig splash screen

    * Gebruiker kan nu zijn fantastische auto aanschouwen tot de batterij leeg is
      of dat hij beslist een nieuwe aan te maken

    * Het menu scherm laat naast aanmaken van een (nieuwe) auto nu ook toe de
      laatst aangemaakte auto terug te zien.

    * Aangemaakte auto is bewaard in Shared preferences , had ook via tekstbestand
      kunnen werken en dat uploaden naar ftp server , maar leek me onnodig .
      (Bij een lijst van aangemaakte auto's zou ik dat wel gedaan hebben, maar
      zover ben ik niet gegaan)

- Besluit :

  Doelstellingen lijken me bereikt.
  Uitwerking kan beter (layout , code cleanup, meer DRY maken) maar voor de
  beperkte oplevertijd lijkt me dit een aanvaardbaar product.

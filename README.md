# spring-cache-async-schedule-events
Spring framework functionality of caching, asyncronous and scheduled method invoking, and events calling

### Cache Task 1
Uruchom aplikację – obserwuj wyniki
Skonfiguruj mechanizm cachowania
Dodaj obsługę cachowania na CacheableCarRepository dla metody getById. Pamiętaj o inwalidacji i uaktualnieniu cache’a
Uruchom aplikację – obserwuj wyniki

### Cache Task 2
Uruchom aplikację – obserwuj wyniki
W interfejsie CarRepository dodano metodę getAll. Dodaj obsługę cachowania także dla tej metody. Zadbaj o to żeby cachowanie innych metod nadal działało prawidłowo
Uruchom aplikację – obserwuj wyniki

### Cache Task 3
Uruchom aplikację – obserwuj wyniki
W interfejsie CarRepository dodano metodę Collection<Car> getByIds(Long... ids). 
Zaimplementuj metodę wykorzystując getId w ten sposób aby pobierać tylko te samochody, które nie znajdują się w cache’u
Uruchom aplikację – obserwuj wyniki

### Async Task 1
Uruchom aplikację – obserwuj wyniki
Zmień implementację w ten sposób aby 100 wywołań metody ImportantThingManager#doImportantThing wykonało się w 10 wątkach
Uruchom aplikację – obserwuj wyniki

### Async Task 2
Uruchom aplikację – obserwuj wyniki
Zmień implementację w ten sposób aby wywołać metodę Calculator#calculateRandomDistribution 100 razy w 10 wątkach i zebrać wyniki do jednej mapy
Uruchom aplikację – obserwuj wyniki

### Async Task 3
Uruchom aplikację – obserwuj wyniki
Metoda HtmlReader#readHtml pozwala pobrać źródła dowolnej strony internetowej.
Stwórz mechanizm, który korzystając z powyższej metody co 30sek pobierze źródła strony http://onet.pl i sprawdzi czy zmieniły się one od ostatniego razu
Uruchom aplikację – obserwuj wyniki

### Async Task 4
Wykonaj zadanie 3. korzystając bezpośrednio z interfejsów TaskScheduler i Trigger. 

### Events Task 1
Klasa DataSynchronizer po zakończeniu synchronizacji wywołuje inne komponenty. Zmień implementacje w ten sposób aby zamiast wywoływać inne komponenty wysyłać zdarzenie, które zostanie obsłużone przez te komponenty. Zadbaj o to aby wyświetlany czas był wszędzie taki sam
Uruchom aplikację – obserwuj wyniki

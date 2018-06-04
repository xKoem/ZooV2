**Jak uruchomić?**
-
Wszystkie skrypty zsh należy uruchamiać będąc w katalogu libsvm/libsvm-3.22/java

Na początku trzeba uruchomić za pomocą zsh plik *runner* który zwraca nam informację o średniej skuteczności dla każdego podzbioru dla różnych gamma i nu a następnie podaje informację jakie gamma i nu jest najskuteczniejsze.

Następnie *mistakeArray* z wartościami zwróconymi przez *runner* np. ```./mistakeArray 0.0008 0.17```

Na końcu należy zbudować projekt mavenem ```mvn clean install``` i uruchomić będąc w katalogu głównym projektu. ```java -jar ./target/ZooV2-1.0.jar```

W katalogu libsvm są już stworzone wszystkie pliki które powstaną przy wykonywaniu wymienionych komend

**Interesujące pliki**
-
* src/*
* libsvm/test{1-4}/*
* libsvm/libsvm-3.22/java/
  * runner
  * checker
  * mistakeArray


**Wyniki działania programu:**
-

Znalezione najlepsze gamma i nu:
```
gamma: 0.0008 nu: 0.17 95.330459770114942
```

Macierz pomyłek:
```
 __1__2__3__4__5__6__7
1| 41 0  0  0  0  0  0
2| 0  20 0  0  0  0  0
3| 0  1  3  1  0  0  0
4| 0  0  0  13 0  0  0
5| 0  0  0  0  4  0  0
6| 0  0  0  0  0  8  0
7| 0  0  0  0  0  3  7
```
Gdzie w numery kolumn oznaczaja oczekiwany wynik a nr wiersza oznacza ten który dostaliśmy

**Dane**
-
Posiadamy 7 klas zwierzat, kazde zwierzę posiada 16 atrybutów na podstawie których klasyfikujemy je.

Zbiór danych został podzielony na 4 podzbiory, które można znaleźć w kalalogach test1 - 4

W pliku test.data jest zbior ktory jest uzywany do sprawdzenia poprawnosci uczenia

W pliku train.data jest zbiór złożony z pozostałych 3 podzbiorów używany do uczenia.

Plik trainoutput to plik z naszym modelem który dostajemy po treningu.

Plik output to plik w którym mamy informację jak program svm_predict uważa że powinny być nasze dane sklasyfikowane. Co weryfikujemy później w naszej aplikacji Java.

Program mistakeArray to zwykly parser wypluwający 2 pliki: correctValues - wartości które powinniśmy otrzymać (nasze orginalne) oraz predictedValues (wartości które zwrócił nam svm_predict)

W programie Java tworzymy macierz pomyłek gdzie numery wierszy to klasy a kolumny to miejsce ile razy dla danej klasy jaka klasa została wybrana przez svm_predict
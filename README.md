# Kalkulator czasu

Jest to aplikacja kalkulatora czasu dla systemu Android, która umożliwia wykonywanie prostych obliczeń dotyczących czasu, takich jak dodawanie i odejmowanie dni oraz operacje na wartościach w formacie HH:MM:SS.

## Funkcje

Aplikacja składa się z trzech aktywności, które umożliwiają różne rodzaje obliczeń:

1. **Aktywność startowa** - Ta aktywność umożliwia przejście do aktywności kalkulatora obliczeń na datach lub HMS.

2. **Aktywność do obliczeń na datach** - Ta aktywność pozwala na obliczenia związane z datami. Użytkownik może ustawić dwie daty za pomocą pickerów, a następnie wyświetlane są informacje dotyczące liczby dni między tymi datami oraz liczby dni roboczych. Istnieje również możliwość wpisania liczby dni, jaka powinna dzielić te daty, wówczas drugi picker automatycznie przestawia się na odpowiednią wartość. Kalkulator uwzględnia wszystkie święta oraz dni wolne w Polsce, w tym również ruchome święta.

3. **Aktywność do obliczeń HMS** - Ta aktywność umożliwia obliczenia na wartościach czasu w formacie HH:MM:SS. Użytkownik wpisuje wartości godzin, minut i sekund w polach typu PlainText. Następnie może wybrać przycisk "+" lub "-" w celu dodania lub odjęcia tych wartości. Wynik jest wyświetlany w górnym polu, a dolne pola są czyszczone. Przycisk "AC" czyści wszystkie pola, ustawiając je na wartość "0".

### Wymagania techniczne

Aplikacja została stworzona z wykorzystaniem następujących technologii i narzędzi:

- Środowisko programistyczne: Android Studio (API 27)
- Język programowania: Kotlin

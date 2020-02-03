# weatherme

An app for planning outdoor adventures.

Update: This project was put away for some time, but I've got free time to start working on it again.

## Todos:
### Weather
#### Accuracy comparison
X Will need DB read/write - pref jdbc, psql
- Each run should:
- - Add forecast for next day at 2PM -> to-db
- - Grab actuals from previous day at 2PM -> to-db
- - Compare db entries with same day?
- - - This makes it so the program can be run at any point
- - - during a given day, not at the exact time of previous
- - - forecast.

- Climbing Route Finding
- - Find all routes with a passed max difficulty, passed location and passed max distance
- - Deliver Weather for climbing areas within passed difficulty

- Usage
- - lein run with arguments or interactive prompt? TBD

## Usage

Will run via leiningen, via lein run.

    $ java -jar weatherme-0.1.0-standalone.jar [args]

## Options

TBD

## Examples

TBD

### Bugs

TBD

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

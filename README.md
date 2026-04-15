# CropSim (Project/stage name)
Built With
Java — core language

JUnit 5 — unit testing

# Farm Field Simulation (Project Design)

A Java object-oriented programming assignment modelling a grid-based farm simulation.
This is a pure backend/logic project with no GUI — all functionality is verified through
a JUnit 5 test suite. A Farmer manages a Farm made up of Plots, planting and tending
to Crops that grow, dry out, and die based on daily simulation logic.

---

## How It Works

Each day is simulated by calling `simulateDay()`. Crops consume water, grow if hydrated,
and die if left dry for too long. A mature crop can be harvested and added to the
farmer's harvested crop list. Dead crops must be manually cleaned from their plot
before a new crop can be planted.

---

## Crop Types

| Crop        | Growth Rate | Maturity Threshold | Dry Day Tolerance |
|-------------|-------------|-------------------|-------------------|
| Lettuce     | 3 / day     | 30                | 1 day             |
| Corn        | 5 / day     | 75                | 2 days            |
| Strawberry  | 6 / day     | 60                | 0 days            |

- All crops start with a water level of 2
- Watering adds 2 to the water level (capped at 10)
- Water level decreases by 1 each simulated day
- Growth only occurs when water level is above 0

---

## Project Structure

farm/
├── crop/
│ ├── Crop.java # Crop state: growth, water, death, harvest logic
│ ├── CropType.java # Enum defining LETTUCE, CORN, STRAWBERRY with stats
│ └── CropTest.java # Unit tests for Crop
├── field/
│ ├── Plot.java # Single grid cell, holds one crop
│ ├── Farm.java # 2D grid of Plots, daily simulation, thirst finder
│ ├── PlotTest.java # Unit tests for Plot
│ └── FarmTest.java # Unit tests for Farm
└── person/
├── Farmer.java # Plants, waters, harvests, and cleans plots
└── FarmerTest.java # Unit tests for Farmer


---

## Key Classes

- `Crop` — tracks growth level, water level, dry days, and death state. Supports `water()`, `simulateDay()`, `isMature()`, and `harvest()`
- `CropType` — enum defining each crop's growth rate and maturity threshold
- `Plot` — a single farm cell that holds one crop. Throws `IllegalStateException` if you try to plant on an occupied plot or remove from an empty one
- `Farm` — a 2D grid of Plots. Provides `simulateDay()` to advance all crops and `findMostThirstyCrop()` to locate the crop with the lowest water level
- `Farmer` — the main actor. Can plant, water, harvest, and clean individual plots or all plots of a given crop type. Also exposes `waterMostThirstyCrop()` which delegates to the farm

---
<img width="381" height="571" alt="image" src="https://github.com/user-attachments/assets/df800da5-44c7-4652-b7d0-24921b647bb8" />

## Tests

18 JUnit 5 tests across 4 test classes — all passing.

| Test Class   | Tests | Coverage                                              |
|--------------|-------|-------------------------------------------------------|
| CropTest     | 6     | Water decay, growth, death thresholds, maturity, harvest |
| PlotTest     | 3     | Plant, remove, dead crop detection, toString          |
| FarmTest     | 4     | Grid creation, simulateDay, thirst finder, toString   |
| FarmerTest   | 5     | Plant, water, harvest, clean, story scenarios         |

### Running the tests

```bash
javac -cp junit6all.jar -d out crop/*.java field/*.java person/*.java
java -cp "out:junit6all.jar" org.junit.platform.console.ConsoleLauncher execute --scan-classpath=out



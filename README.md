# GardeningSystem

# Initial Status:

## Rose:
- **Age**: 100  
- **Water Requirement**: 15  
- **Max Temperature**: 100  
- **Min Temperature**: 60  
- **Water Level**: 10  
- **Pests**: Aphids, Beetles, Cutworms  
- **isAlive**: true  

## Sunflower:
- **Age**: 100  
- **Water Requirement**: 20  
- **Max Temperature**: 98  
- **Min Temperature**: 50  
- **Water Level**: 10  
- **Pests**: Aphids, Beetles, Cutworms  
- **isAlive**: true  

## Lily:
- **Age**: 100  
- **Water Requirement**: 25  
- **Max Temperature**: 95  
- **Min Temperature**: 55  
- **Water Level**: 20  
- **Pests**: Aphids, Beetles, SpiderMites  
- **isAlive**: true  

## Tomato:
- **Age**: 100  
- **Water Requirement**: 12  
- **Max Temperature**: 98  
- **Min Temperature**: 60  
- **Water Level**: 8  
- **Pests**: Aphids, WhiteFlies, HornWorms  
- **isAlive**: true  

## Tulip:
- **Age**: 100  
- **Water Requirement**: 18  
- **Max Temperature**: 100  
- **Min Temperature**: 56  
- **Water Level**: 14  
- **Pests**: Aphids, SpiderMites, Bulbfly  
- **isAlive**: true  

## Lemon:
- **Age**: 100  
- **Water Requirement**: 23  
- **Max Temperature**: 104  
- **Min Temperature**: 50  
- **Water Level**: 18  
- **Pests**: Aphids, LeafMiner  
- **isAlive**: true  

## Orange:
- **Age**: 100  
- **Water Requirement**: 15  
- **Max Temperature**: 105  
- **Min Temperature**: 53  
- **Water Level**: 10  
- **Pests**: Aphids, WhiteFlies  
- **isAlive**: true  

## Apple:
- **Age**: 100  
- **Water Requirement**: 15  
- **Max Temperature**: 102  
- **Min Temperature**: 50  
- **Water Level**: 10  
- **Pests**: Aphids, Caterpillars, Codling Moth  
- **isAlive**: true

# Plant Health

## Life:
- Each day, the age of the plant is reduced by **2 units**.

## Pest Attack:
- The age of the plant is reduced based on the severity of pests:
- **Moderate pests**: **5 units**.
- **Severe pests**: **10 units**.

## Pesticide:
- Improves the age of the plant by **5 units**.

## Water Level:
- The age of the plant is reduced based on the water level:
- Just above the required amount: **3 units**.
- More than twice the required amount: **10 units**.

## Temperature:
- The age of the plant is reduced based on the temperature level:
- Temperature reduces water level: **2 units**.
- Marginal change: **2 units**.
- Untolerable temperature (above 100 or below 50): **10 units**.
- Regulator increases the age by **4 units**.

# Subsystems

## Day Simulation:
- Runs counter for the day
- One day is simulated as 15 seconds.

## Plant Management Subsystem:
- Tracks all plants in the garden.
- Supports automated and manual plant addition.
- Removes dead plants.
- Runs at the start of each day.
- Notifies the weather controller to generate random weather for the day.
- Reduces plant health by 5 units.
- Manages the life cycle of plants.

## Weather Subsystem:
- Simulates weather for the day, chosen at random: **Sunny**, **Cloudy**, **Rainy**.
- Updates temperature and humidity based on the weather condition.
- Influences garden operations like sprinkler activation, pest behavior, pesticide effectiveness, and temperature control.
- Randomizes weather conditions for the next day.

### Sunny:
- Random temperature generated between **80 to 105**.
- No rain.
- Sunny days trigger the sprinkler system.
- 30% chance of pest activity.
- Pesticide, if applied, remains effective.
- **Pests**: Beetles, HornWorms, SpiderMites.

### Cloudy:
- Random temperature generated between **60 to 75**.
- 30% chance of rain.
- Triggers the sprinkler system depending on the water level.
- Pesticide, if applied, stays effective **70%** of the time (rain may wash it away).
- 40% chance of pest activity.
- **Pests**: Bulbfly, CodlingMoth, LeafMiner.

### Rainy:
- Random temperature generated between **45 to 55**.
- Notifies the sprinkler system to switch off.
- Notifies the temperature module to regulate temperature.
- Pests are more active on rainy days.
- Rain washes away the pesticide.
- **Pests**: Aphids, Caterpillars, Cutworms.

## Sprinkler Subsystem:
- Gets weather for the day from the weather controller.
- Calculates the current water level and water requirement for the garden.
- Activates sprinklers when:
  - Temperature exceeds a threshold.
  - During dry weather.
  - Water level goes below the actual water requirement.
- Sprinklers are notified to switch off when it’s raining.

## Temperature Subsystem:
- Gets the current temperature and weather from the weather module.
- Activates the temperature regulator when the temperature falls below or exceeds the optimum range.
- Plants’ health is affected in intolerable temperature conditions.

## Rain Subsystem:
- Gets weather for the day from the weather controller.
- Generates:
  - **5 units** of rainfall on cloudy weather with a chance of rain.
  - **15 to 22 units** of rainfall randomly on rainy weather.
- Plant health is affected if there is excessive water in the garden.

## Pest Subsystem:
- Gets weather for the day from the weather controller.
- No pest activity if pesticides are effective.
- **Sunny weather**: 30% pest activity.  
  **Pests**: Beetles, HornWorms, SpiderMites.
- **Cloudy weather**: 40% pest activity.  
  **Pests**: Bulbfly, CodlingMoth, LeafMiner.
- **Rainy weather**: Pests are highly active.  
  **Pests**: Aphids, Caterpillars, Cutworms.
- Devises a plan of attack and targets pest-specific vulnerable plants.
- Plant health is affected based on the severity of pest attacks.

## Pesticide Subsystem:
- Gets weather for the day from the weather controller.
- Pesticide thread runs once every **4 days**.
- Pesticide remains effective on sunny days and cloudy days without rain, preventing pest attacks.
- Pesticide is washed away on rainy days, allowing pests to return.
- Logs treatment actions.
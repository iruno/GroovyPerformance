import groovy.json.JsonOutput
import org.junit.Test

class BuildJson {

    class Ingredient {
        String name
        String unit
        Integer pcs

        Ingredient(String name, String unit, Integer pcs) {
            this.name = name
            this.unit = unit
            this.pcs = pcs
        }
    }

    class Recipe {
        String name
        String description
        String yield

        List<Ingredient> ingredients
        List<String> directions
    }

    final Recipe recipe = new Recipe(
            name: "Sugar Waffles",
            description: "Best waffles I've ever made. Light and crispy.",
            yield: "20-30 waffles",

            ingredients: [
                    new Ingredient("flour", "g", 500),
                    new Ingredient("sugar", "g", 400),
                    new Ingredient("vanilla sugar", "g", 70),
                    new Ingredient("eggs, beaten", "pcs", 5),
                    new Ingredient("butter, melted", "g", 400)
            ],

            directions: [
                    "Mix flour, sugar and vanilla sugar.",
                    "Add butter and eggs.",
                    "Leave to rest for minute 2 hours in the fridge.",
                    "Put dough on the waffle maker (use two teaspoons or tablespoons according to the size of waffles you would like to make).",
                    "Bake for a few minutes until the waffles are golden brown.",
                    "Leave to cool. You can keep these waffles for some time in a plastic box, but couldn't tell you how long as they never last for more than 2-3 days.",
                    "Enjoy!"
            ]
    )

    @Test
    void benchmark() {
        String result1

        benchmark {
            'Using JsonOutput' {
                result1 = JsonOutput.toJson(recipe)
            }

        }.prettyPrint()

        println(result1)
    }

}

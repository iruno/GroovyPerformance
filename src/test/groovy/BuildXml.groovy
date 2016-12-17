import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.junit.Test

class BuildXml {

    static String usingMarkupBuilder() {

        def writer = new StringWriter()
        def xml = new MarkupBuilder(new IndentPrinter(new PrintWriter(writer), "", false))
        xml.setDoubleQuotes(true)
        xml.Recipe {

            Name("Sugar Waffles")
            Description("Best waffles I've ever made. Light and crispy.")
            Yield("20-30 waffles")
            Ingredients {
                Ingredient {
                    Qty("unit": "g", 500)
                    Item("flour")
                }

                Ingredient {
                    Qty("unit": "g", 400)
                    Item("sugar")
                }

                Ingredient {
                    Qty("unit": "g", 70)
                    Item("vanilla sugar")
                }

                Ingredient {
                    Qty("unit": "pcs", 5)
                    Item("eggs, beaten")
                }

                Ingredient {
                    Qty("unit": "g", 400)
                    Item("butter, melted")
                }
            }
            Directions {
                Step("Mix flour, sugar and vanilla sugar.")
                Step("Add butter and eggs.")
                Step("Leave to rest for minute 2 hours in the fridge.")
                Step("Put dough on the waffle maker (use two teaspoons or tablespoons according to the size of waffles you would like to make).")
                Step("Bake for a few minutes until the waffles are golden brown.")
                Step("Leave to cool. You can keep these waffles for some time in a plastic box, but couldn't tell you how long as they never last for more than 2-3 days.")
                Step("Enjoy!")
            }
        }

        writer.toString()
    }

    static String usingXmlNodePrinter() {

        def Recipe = new Node(null, 'Recipe')
        Recipe.appendNode("Name", "Sugar Waffles")
        Recipe.appendNode("Description", "Best waffles I've ever made. Light and crispy.")
        Recipe.appendNode("Yield", "20-30 waffles")
        def Ingredients = Recipe.appendNode("Ingredients")

        def Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 500)
        Ingredient.appendNode("Item", "flour")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 400)
        Ingredient.appendNode("Item", "sugar")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 70)
        Ingredient.appendNode("Item", "vanilla sugar")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "pcs"], 5)
        Ingredient.appendNode("Item", "eggs, beaten")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 400)
        Ingredient.appendNode("Item", "butter, melted")

        def Directions = Recipe.appendNode("Directions")
        Directions.appendNode("Step", "Mix flour, sugar and vanilla sugar.")

        Directions.appendNode("Step", "Add butter and eggs.")
        Directions.appendNode("Step", "Leave to rest for minute 2 hours in the fridge.")
        Directions.appendNode("Step", "Put dough on the waffle maker (use two teaspoons or tablespoons according to the size of waffles you would like to make).")
        Directions.appendNode("Step", "Bake for a few minutes until the waffles are golden brown.")
        Directions.appendNode("Step", "Leave to cool. You can keep these waffles for some time in a plastic box, but couldn't tell you how long as they never last for more than 2-3 days.")
        Directions.appendNode("Step", "Enjoy!")

        StringWriter writer = new StringWriter()
        new XmlNodePrinter(new IndentPrinter(new PrintWriter(writer), '', false)).print(Recipe)
        writer.toString()
    }

    @CompileStatic
    static String usingXmlNodePrinterWithStatic() {

        def Recipe = new Node(null, 'Recipe')
        Recipe.appendNode("Name", "Sugar Waffles")
        Recipe.appendNode("Description", "Best waffles I've ever made. Light and crispy.")
        Recipe.appendNode("Yield", "20-30 waffles")
        def Ingredients = Recipe.appendNode("Ingredients")

        def Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 500)
        Ingredient.appendNode("Item", "flour")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 400)
        Ingredient.appendNode("Item", "sugar")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 70)
        Ingredient.appendNode("Item", "vanilla sugar")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "pcs"], 5)
        Ingredient.appendNode("Item", "eggs, beaten")

        Ingredient = Ingredients.appendNode("Ingredient")
        Ingredient.appendNode("Qty", ["unit": "g"], 400)
        Ingredient.appendNode("Item", "butter, melted")

        def Directions = Recipe.appendNode("Directions")
        Directions.appendNode("Step", "Mix flour, sugar and vanilla sugar.")

        Directions.appendNode("Step", "Add butter and eggs.")
        Directions.appendNode("Step", "Leave to rest for minute 2 hours in the fridge.")
        Directions.appendNode("Step", "Put dough on the waffle maker (use two teaspoons or tablespoons according to the size of waffles you would like to make).")
        Directions.appendNode("Step", "Bake for a few minutes until the waffles are golden brown.")
        Directions.appendNode("Step", "Leave to cool. You can keep these waffles for some time in a plastic box, but couldn't tell you how long as they never last for more than 2-3 days.")
        Directions.appendNode("Step", "Enjoy!")

        StringWriter writer = new StringWriter()
        new XmlNodePrinter(new IndentPrinter(new PrintWriter(writer), '', false)).print(Recipe)
        writer.toString()
    }

    @Test
    void benchmark() {
        String resultMarkupBuilder
        String resultXmlNodePrinter
        String resultXmlNodePrinterWithStatic

        benchmark {
            'Using MarkupBuilder' {
                resultMarkupBuilder = usingMarkupBuilder()
            }

            'Using XmlNodePrinter' {
                resultXmlNodePrinter = usingXmlNodePrinter()
            }
            'Using XmlNodePrinter + CompileStatic' {
                resultXmlNodePrinterWithStatic = usingXmlNodePrinterWithStatic()
            }
        }.prettyPrint()

        assert resultMarkupBuilder == resultXmlNodePrinter
        assert resultXmlNodePrinter == resultXmlNodePrinterWithStatic
    }
}

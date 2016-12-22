import groovy.transform.CompileStatic
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.NodeChild
import groovy.util.slurpersupport.NodeChildren
import org.junit.Test

class ParseXml {

    static String xmlString = BuildXml.usingXmlNodePrinterWithStatic()

    // Task: find the quantity of 'vanilla sugar'

    static Integer groovyWay() {

        def recipe = new XmlSlurper().parseText(xmlString)
        recipe?.'Ingredients'?.'Ingredient'?.find {
            it.Item == 'vanilla sugar'
        }?.Qty?.toInteger()
    }

    @CompileStatic
    static Integer nodeChildren() {

        GPathResult recipe = new XmlSlurper().parseText(xmlString)

        def ingredients = recipe.getProperty('Ingredients') as NodeChildren

        if (!ingredients)
            return

        for (Iterator iter = ingredients.children().iterator(); iter.hasNext();) {
            def ingredient = iter.next() as NodeChild

            String itemName = ingredient.getProperty('Item')

            if (itemName == 'vanilla sugar') {
                String qty = ingredient.getProperty('Qty')
                return qty.toInteger()
            }
        }
    }

    @CompileStatic
    static Integer nodeChildren2() {

        GPathResult recipe = new XmlSlurper().parseText(xmlString)

        NodeChildren ingredients = new NodeChildren(recipe, 'Ingredients', null)

        if (!ingredients)
            return

        for (Iterator iter = ingredients.children().iterator(); iter.hasNext();) {
            def ingredient = iter.next() as NodeChild

            String itemName = ingredient.getProperty('Item')

            if (itemName == 'vanilla sugar') {
                String qty = ingredient.getProperty('Qty')
                return qty.toInteger()
            }
        }
    }

    //@CompileStatic
    static Integer nodeChildren3() {

        GPathResult recipe = new XmlSlurper().parseText(xmlString)

        NodeChildren ingredients = new NodeChildren(recipe, 'Ingredients', null)

        if (!ingredients)
            return

        for (ingredient in ingredients.children()) {
            String itemName = ingredient.getProperty('Item')

            if (itemName == 'vanilla sugar') {
                String qty = ingredient.getProperty('Qty')
                return qty.toInteger()
            }
        }
    }

    @Test
    void benchmark() {
        Integer result1
        Integer result2
        Integer result3
        Integer result4

        benchmark {
            groovyWay {
                result1 = groovyWay()
            }
            nodeChildren {
                result2 = nodeChildren()
            }
            nodeChildren2 {
                result3 = nodeChildren2()
            }
            nodeChildren3 {
                result4 = nodeChildren3()
            }

        }.prettyPrint()

        println result1

        assert result1 == result2
        assert result2 == result3
        assert result3 == result4
    }
}

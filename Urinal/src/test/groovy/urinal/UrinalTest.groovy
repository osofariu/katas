package urinal;

import spock.lang.Specification

/**
 * Created by ovi on 9/23/14.
 */
class UrinalTest extends Specification {

    def "when there's one total and it is open grab that one"() {
        given:
        def urinals = [0]
        StoolSet stools = new StoolSet(urinals)

        expect:
        stools.allocate() == 0
    }

    def "when there's one total and it is not open you can't allocate a urinal"() {
        given:
        def urinals = [1]
        StoolSet stools = new StoolSet(urinals)

        expect:
        stools.allocate() == -1
    }

    def "find urinal furthest from the door that is not used and is not next to another dude"() {
        given:
        def urinals = [0, 0, 0, 1]
        StoolSet stools = new StoolSet(urinals)

        expect:
        stools.allocate() == 1
    }

    def "find stool when no three consecutive stalls are open"() {
        given:
        def urinals = [1, 0, 0, 1]
        StoolSet stools = new StoolSet(urinals)

        expect:
        stools.allocate() == 2
    }

    def "find stool when no two consecutive stalls are open"() {
        given:
        def urinals = [0, 1, 0, 1]
        StoolSet stools = new StoolSet(urinals)

        expect:
        stools.allocate() == 2
    }
}

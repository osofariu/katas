class Cell {

    def reciprocal = [N: 'S', NE: 'SW', E: 'W', SE: 'NW', S: 'N', SW: 'NE', W: 'E', NW: 'SE'] as HashMap
    def transitiveAssociations = [[['NW', 'N'], 'E'], [['N', 'NE'], 'E'], [['NE', 'E'], 'S'], [['E', 'SE'], 'S'],
                                  [['SE', 'S'], 'W'], [['SW', 'S'], 'E'], [['W', 'SW'], 'S'], [['NW', 'W'], 'S'],
                                  [['W', 'N'], 'NE'], [['N', 'E'], 'SE'], [['E', 'S'], 'SW'], [['W', 'S'], 'SE']]

    def neighbors = [] as HashMap
    def statusValue = 1
    def nextStatusLookup = [0, 0, 1]
    def name = ""

    def static isAlive(Cell cell) {
        cell != null && cell.statusValue == 1
    }

    Cell iterate() {
        statusValue = nextStatusLookup[countAliveNeighbors()]
        this;
    }

    int countAliveNeighbors() {
        def alive = 0
        neighbors.values().each { cell ->
            alive += cell.statusValue
        }
        alive
    }

    def associate(String direction, Cell cell) {
        makeAssociationWith(direction, cell)
        cell.makeAssociationWith(getReciprocal(direction), this)
        this.makeTransitiveAssociations()
    }

    private makeTransitiveAssociations() {
        transitiveAssociations.each {
            def assocMap = buildMap(it)
            def firstNeighbor = getNeighbor(assocMap['firstDirection'])
            def secondNeighbor = getNeighbor(assocMap['secondDirection'])
            if (isAlive(firstNeighbor) && isAlive(secondNeighbor)) {
                firstNeighbor.makeAssociationWith(assocMap['navigation'], secondNeighbor)
                secondNeighbor.makeAssociationWith(getReciprocal(assocMap['navigation']), firstNeighbor)
            }
        }
    }

    def buildMap(ArrayList<Serializable> transitiveAssociation) {
        def map = [] as HashMap<String, String>
        def associations = transitiveAssociation[0] as List
        map['firstDirection'] = associations[0]
        map['secondDirection'] = associations[1]
        map['navigation'] = transitiveAssociation[1]
        map
    }

    String getReciprocal(direction) {
        reciprocal[direction]
    }

    Cell getNeighbor(String direction) {
        neighbors[direction]
    }


    def makeAssociationWith(String direction, Cell cell) {
        neighbors.put(direction, cell)
    }
}

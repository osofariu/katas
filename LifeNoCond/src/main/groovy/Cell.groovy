class Cell {
    def directions = ['N', 'NE', 'E', 'SE', 'S', 'SW', 'W', 'NW']
    def reciprocal = [N: 'S', NE: 'SW', E: 'W', SE: 'NW', S: 'N', SW: 'NE', W: 'E', NW: 'SE'] as HashMap
    def transitiveAssociations = [[['NW', 'N'], 'E'], [['N', 'NE'], 'E'], [['NE', 'E'], 'S'], [['E', 'SE'], 'S'],
                                  [['SE', 'S'], 'W'], [['SW', 'S'], 'E'], [['W', 'SW'], 'S'], [['NW', 'W'], 'S'],
                                  [['W', 'N'], 'NE'], [['N', 'E'], 'SE'], [['E', 'S'], 'SW'], [['W', 'S'], 'SE']]

    def neighbors = [] as HashMap
    def statusValue = 0
    def nextStatusLookup = [0, 0, 1, 1, 0]
    def thirdCellHack   = [0, 0, 0, 1, 0]
    def name = ""

    def connectWithNeighbors() {
        def undefinedDirections = directions - neighbors.keySet()
        directions.each { direction ->
            undefinedDirections.each {
                neighbors.put(it, new Cell())
            }
        }
    }

    Cell iterate() {
        def aliveNeighbors = countAliveNeighbors()
        statusValue = statusValue * nextStatusLookup[aliveNeighbors] +        // keep dead cells dead
                (1 - statusValue) * thirdCellHack[aliveNeighbors]             // unless dead cell has 3 neighbors
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
        connectWithNeighbors()
        cell.connectWithNeighbors()
        makeAssociationWith(cell, direction)
        cell.makeAssociationWith(this, getReciprocal(direction))
        this.makeTransitiveAssociations()
    }

    def makeAssociationWith(Cell cell, String direction) {
        neighbors.put(direction, cell)
    }

    private makeTransitiveAssociations() {
        transitiveAssociations.each {
            def assocMap = buildMap(it)
            def firstNeighbor = getNeighbor(assocMap['firstDirection'])
            def secondNeighbor = getNeighbor(assocMap['secondDirection'])
            firstNeighbor.makeAssociationWith(secondNeighbor, assocMap['navigation'])
            secondNeighbor.makeAssociationWith(firstNeighbor, getReciprocal(assocMap['navigation']))
        }
    }

    def static buildMap(ArrayList<Serializable> transitiveAssociation) {
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


}

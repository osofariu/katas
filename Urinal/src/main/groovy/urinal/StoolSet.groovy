package urinal

/**
 * Created by ovi on 9/23/14.
 */
class StoolSet {

    def myStools = [];

    StoolSet(stools) {
        myStools = stools
    }

    def allocate() {
        if (myStools.size() <= 2) {
            return myStools.findIndexOf { it == 0 }
        }
        def idealIndex = findIdealStool()
        if (idealIndex != -1) {
            return idealIndex
        }
        def nextToOne = findStoolNextToOneDude()
        if (nextToOne != -1) {
            return nextToOne
        }
        return findStoolNextToTwoDudes()
    }

    def findStoolNextToTwoDudes() {
        myStools.findLastIndexOf {it == 0}
    }

    def findStoolNextToOneDude() {
        def lastIndex = myStools.findLastIndexOf { it == 0 }
        if (lastIndex == myStools.size() - 1) {
            return lastIndex    // last stool has no more than one neighbors
        }
        for (int index = lastIndex; index > 0; index--) {
            if (myStools[index] == 0 && myStools[index + 1] == 0 ||
                    myStools[index] == 0 && myStools[index - 1] == 0)
                return index
        }
        return -1
    }

    private int findIdealStool() {
        def lastIndex = myStools.findLastIndexOf { it == 0 }
        for (int index = lastIndex - 1; index >= 0; index--) {
            if (myStools[index + 1] == 0 && myStools[index] == 0 && myStools[index - 1] == 0)
                return index;
        }
        return -1
    }

}
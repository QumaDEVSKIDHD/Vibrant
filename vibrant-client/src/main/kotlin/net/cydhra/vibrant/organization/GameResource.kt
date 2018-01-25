package net.cydhra.vibrant.organization

/**
 *
 */
abstract class GameResource {

    /**
     * This function is called once per tick and allows the resource to update state in the client application or sending packets to the
     * remote server to update its state respectively.
     */
    abstract fun updateResourceState()
}
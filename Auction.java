import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        Lot searchLot = null;
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)){
            Iterator<Lot> it = lots.iterator();
            
            while (it.hasNext()){
                Lot tempLot = it.next();
                if(tempLot.getNumber() == lotNumber){
                    searchLot = tempLot;
                }
            }
        }
        
        return searchLot;
    }
    /**
     * Metodo que borra un lote de la subasta
     */
    public void removeLot(int LotNumber){
        Iterator<Lot> it = lots.iterator();
        while(it.hasNext()){
            Lot tempLot = it.next();
            if(tempLot.getNumber() == LotNumber){
                it.remove();
            }
        }
    }
    
    /**
     * Metodo que muestra por pantalla las características de los objetos 
     * que se están subastando
     */
    public void close(){
        for(Lot lote : lots){
            System.out.println("Número de lote: " + lote.getNumber());
            System.out.println("Descripción: " + lote.getDescription());
            if(lote.getHighestBid() != null){
                Bid highestBid = lote.getHighestBid();
                Person bidder = highestBid.getBidder();
                System.out.println("Puja mas alta: " + highestBid.getValue());
                System.out.println("Autor de la puja: " + bidder.getName() +
                                    "\n\n");
            }
            else{
                System.out.println("\n\n");
            }
        }
    }
    /**
     * Devuelve un Array con todos los lotes por los que NO ha habido ninguna
     * puja aún
     */
    public ArrayList getUnsold (){
        ArrayList<Lot> unsold = new ArrayList<Lot>();
        for(Lot lote : lots){
            if(lote.getHighestBid() == null){
                unsold.add(lote);
            }
        }
        return unsold;
    }
}

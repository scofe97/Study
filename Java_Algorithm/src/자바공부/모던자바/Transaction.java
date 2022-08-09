package 자바공부.모던자바;

public class Transaction implements Comparable<Transaction> {
	 private Trader trader;
	    private int year;
	    private int value;

	    public Transaction(Trader trader, int year, int value) {
	        this.trader = trader;
	        this.year = year;
	        this.value = value;
	    }

	    public Trader getTrader() {
	        return trader;
	    }

	    public int getYear() {
	        return year;
	    }

	    public int getValue() {
	        return value;
	    }
	    
	    @Override
	    public String toString() {
	    	return "trader : " + getTrader() + "  year : " + getYear()+ "  value : " + getValue();
	    }

		@Override
		public int compareTo(Transaction o) {
			return this.getValue() - o.getValue();
		}
	    
	    
	
}

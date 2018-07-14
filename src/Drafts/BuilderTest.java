package Drafts;

public class BuilderTest {
	
	private String name;
	private int id;
	private String address;
	
	public BuilderTest(Builder builder) {
		this.name = builder.name;
		this.id = builder.id;
		this.address = builder.address;
	}
	
	public String printOut() {
		return ("Name: " + name + ", ID: " + id + ", Address: " + address + ".");
	}
	
	
	static class Builder {
		private String name;
		private int id;
		private String address;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder id(int id) {
			this.id = id;
			return this;
		}
		
		public Builder address(String address) {
			this.address = address;
			return this;
		}
		
		public BuilderTest build() {
			return new BuilderTest(this);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BuilderTest one = new BuilderTest.Builder().name("Handsome").id(13).address("San Jose").build();
		
		BuilderTest two = new BuilderTest.Builder().build();
		System.out.println(one.printOut());
		System.out.println(two.printOut());
		
	}

}

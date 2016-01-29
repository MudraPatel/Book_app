
class sorting{
	public static void main(String args[]){
		int a[]={10,20,50,30,40,80};
		int i,temp;
		for(i=0;i<6;i++){
			if(a[i]>a[i+1]){
				temp =a[i+1];
				a[i+1]=a[i];
				a[i]=temp;
			}
		}
	}
}
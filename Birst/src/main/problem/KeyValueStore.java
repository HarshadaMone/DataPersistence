package main.problem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// Hashmap functions create, delete,get,update,getHashMap,Show Contents and Reset
public class KeyValueStore {

	private HashMap<String, String> store = new HashMap<>();
	

	public KeyValueStore() {
		// TODO Auto-generated constructor stub
	}
	public KeyValueStore(HashMap<String,String> store){
		this.store = store; 
	}


	public HashMap<String, String> getStore() {
		return store;
	}
	public void getAll() {

		Set<Entry<String, String>> s = store.entrySet();
		Iterator itr = s.iterator();
		while(itr.hasNext()){
			Map.Entry me = (Entry) itr.next();
			System.out.println(me.getKey()+"="+me.getValue());
		}
		
	
	}
	
	/*
	 * 
	 */
	public Boolean create(String key, String value){
		if(store.containsKey(key)){
			System.out.println("## Key "+key+" already exists ##");
			return false;
		}else if(key.matches("[a-zA-Z/? ]*")){
			store.put(key, value);
			return true;
		}else{
			System.out.println("Input is invalid");
			return false;
		}
	}
	
	public boolean update(String key, String value){
		if(store.containsKey(key)){
			store.put(key, value);
			return true;
		}else{
			System.out.println("Key not found");
			return false;
		}
	}
	
	public boolean delete(String key){
		if(store.containsKey(key)){
			store.remove(key);
			return true;
		}else{
			System.out.println("## Key "+key+" does not exist ##");
			return false;
		}
	}	
	
	public boolean get(String key){
		Boolean found = false;
		Set<Entry<String, String>> s = store.entrySet();
		Iterator<Entry<String, String>> itr = s.iterator();
		while(itr.hasNext()){
			Map.Entry me = itr.next();
			if(((String) me.getKey()).contains(key)){
				System.out.println(me.getKey()+"="+me.getValue());
				found =false;
				break;
			}
		}
		return found;
	}
	
	public void reset(){
		store.clear();
		System.out.println("Cache Cleared");
	}
	
}

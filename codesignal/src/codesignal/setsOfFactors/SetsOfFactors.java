package codesignal.setsOfFactors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
*Write a function that takes an integer n and returns all of the ways that integers not greater than n can be multiplied together in order to equal n. 
*Do not repeat sets of factors - for instance, if the output contains [4, 3], it shouldn't also contain [3, 4]. 
*Both your sets and the numbers in the sets should be sorted in descending order. 1 should only be included in a set if the set also contains n.
**/
public class SetsOfFactors {
	public List setsOfFactors(int n) {
	    List<int[]> result = new ArrayList<>();
	    List<Integer> factor = new ArrayList<>();
	    factor.add(n);
	    factor.add(1);
	    int[] arr = factor.stream().mapToInt(x->x).toArray();
	    result.add(arr);
	  
	    if(!checkPrime(n)) {
	    	boolean check = false;
	        for(int i=n-1; i>1; i--) {
	            factor = new ArrayList<>();
	            if((n%i) == 0) {
	                int firstNumber = i;
	                int nextNumber = n/i;
	                factor.add(firstNumber);
	                factor.add(nextNumber);
	                arr = factor.stream().mapToInt(x->x).toArray();
	                for(int[] alist: result) {
	                	if(checkRepeatList(alist, arr)) {
	                		check = true;
	                	}
	                }
	                if(!check) {
	                	result.add(arr);
	                }
	                while(!checkPrime(nextNumber)) {
	                	factor.remove(factor.size()-1);
	                    for(int j=nextNumber-1; j > 1 ; j--) { 	
	                        if(nextNumber%j == 0) {
	                            factor.add(j);
	                            nextNumber = nextNumber/j;
	                            factor.add(nextNumber);
	                            arr = factor.stream().mapToInt(x->x).toArray();
	                            check = false;
	                            for(int[] alist: result) {
	        	                	if(checkRepeatList(alist, arr)) {
	        	                		check = true;
	        	                	}
	        	                }
	        	                if(!check) {
	        	                	result.add(arr);
	        	                }
	        	                break;
	                        }      
	                    } 
	                }
	                
	            }          
	        }
	    }
	    
	    while(getLastNonPrime(result.get(result.size()-1)) != -1) {
	    	int[] lastArr = result.get(result.size()-1);
	    	int ilast = getLastNonPrime(lastArr);
	    	int[] subArr = Arrays.copyOf(lastArr, lastArr.length+1);
	    	for(int i=2; i< lastArr[ilast]; i++) {
	    		if(lastArr[ilast]%i == 0) {
	    			int fist = lastArr[ilast]/i;
	    			int second = i;
	    			subArr[ilast] = fist;
	    			subArr[subArr.length-1] = second;
	    			result.add(subArr);
	    		}
	    	}
	    	
	    }
	    
	    System.out.println("Test with n= " +n);
	    System.out.println("++++++++++++++++++");
	    for(int[] a: result) {
	    	System.out.println(Arrays.toString(a));
	    }
	    return result;
	}

	boolean checkPrime(int n) {
	    if(n>0 && n <4) {
	        return true;
	    } else {
	        for(int i=2; i < n; i++) {
	            if(n%i == 0) {
	                return false;
	            }
	        }
	        return true;
	    }
	}

	public boolean checkRepeatList(int[] first, int[] second) {
		int[] a = first.clone();
		int[] b = second.clone();
	    Arrays.sort(a);
	    Arrays.sort(b);
	    if(Arrays.equals(a, b)) {
	    	return true;
	    } else {
	    	return false;
	    }
	}
	
	public int getLastNonPrime(int[] arrIn) {
		int last = arrIn.length - 1;
		for(int i=last; i>=0; i--) {
			if(!checkPrime(arrIn[i])) {
				return i;
			}
		}
		return -1;
	}

}

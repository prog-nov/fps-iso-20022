package com.forms.beneform4j.core.util.rsa;

/**
 * @author Kingdom
 * @date   2016-1-13
 * @version  1.0 
 * @description KEY服务   
 */
public interface IKeyService<T extends AbstractKey> {
	
	public  T initKey() throws Exception;
	
	public T getKey() throws Exception;
	
	public String encrypt(String data);
	
	public String decrypt(String data) ;

}

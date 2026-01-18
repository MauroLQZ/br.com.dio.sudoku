package br.com.dio.service;

/* Interface p/ouvir o evento */
public interface EventListener {
	
	//p/propagar o evento q/mandamos p/ele
	void update(final EventEnum eventType);

}

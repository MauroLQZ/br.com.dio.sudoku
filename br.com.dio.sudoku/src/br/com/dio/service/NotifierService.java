package br.com.dio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.dio.service.EventEnum.CLEAR_SPACE;

/* Serviço de notificação */
public class NotifierService {
	
	//recuperamos nossos listerners q/respondem p/um determinado evento
	private final Map<EventEnum, List<EventListener>> listeners = new HashMap<>(){{
        put(CLEAR_SPACE, new ArrayList<>());
    }};

    //estamos inscrevendo um componente do nosso codigo na nossa lista de notificação
    public void subscribe(final EventEnum eventType, EventListener listener){
        var selectedListeners = listeners.get(eventType);
        //pegamos a lista q/tem a referencia e add ela. p/poder cadastrar ela
        selectedListeners.add(listener);
    }

    public void notify(final EventEnum eventType){
        listeners.get(eventType).forEach(l -> l.update(eventType));
    }
}

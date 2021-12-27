package com.moritz.musicsyncapp.model.commuication;

import com.moritz.musicsyncapp.model.client.ClientImpl;
import com.moritz.musicsyncapp.model.client.IClient;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommuicationFactory {

    public static ISendableMessage createMessage (IMessage message, IClient sender, IClient reciever) {
        return new SendableMessageImpl(sender, reciever, message);
    }

    public static ISendableMessage createMessage (String message)
    {
        JSONObject jsonObject = new JSONObject(message);
        IClient sender = new ClientImpl(jsonObject.getString(ISendableMessage.SENDER_KEY));
        IClient reciever = new ClientImpl(jsonObject.getString(ISendableMessage.RECIEVER_KEY));
        IMessage iMessage = buildMessage(jsonObject.getString(ISendableMessage.TYPE_KEY), jsonObject.getString(ISendableMessage.MESSAGE_KEY));
        return new SendableMessageImpl(sender, reciever, iMessage);
    }

    private static IMessage buildMessage (String type, String message)
    {
        try {
            Class aClass = Class.forName(type);

            IMessage msg  = (IMessage)  aClass.getConstructors()[0].newInstance();

            return msg.fromJson(message);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new IMessage() {
            @Override
            public String toJson() {
                return null;
            }

            @Override
            public IMessage fromJson(String json) {
                return null;
            }
        };
    }

}

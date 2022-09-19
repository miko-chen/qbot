package org.jeecg.modules.bot.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author miko
 * @version 0.1
 * @date 2022/9/9 10:52
 */
@Data
@Accessors(chain = true)
public class MessageChain {
    private Type type;
    private String text;
    private String url;
    private Long target;
    private Long groupId;
    private Long senderId;
    private Long targetId;
    private String id;
    private List<MessageChain> origin;
    private int faceId;
    private String name;
    private String imageId;
    private String path;
    private String base64;
    private String voiceId;
    private Long length;
    private String xml;
    private String json;
    private String content;
    private int value;
    private String kind;
    private String title;
    private String summary;
    private String jumpUrl;
    private String pictureUrl;
    private String musicUrl;
    private String brief;
    private List<MessageNode> nodeList;
    private Long size;
    private String code;

    public MessageChain quote(int id,Long targetId,Long groupId,Long senderId,List<MessageChain> origin){
        this.type = Type.Quote;
        this.id = String.valueOf(id);
        this.targetId = targetId;
        this.groupId = groupId;
        this.senderId = senderId;
        this.origin = origin;
        return this;
    }
    public MessageChain quote(int id,Long groupId,Long senderId,List<MessageChain> origin){
        this.type = Type.Quote;
        this.id = String.valueOf(id);
        this.groupId = groupId;
        this.senderId = senderId;
        this.origin = origin;
        return this;
    }
    public MessageChain quote(int id,Long senderId,List<MessageChain> origin){
        this.type = Type.Quote;
        this.id = String.valueOf(id);
        this.groupId = 0L;
        this.senderId = senderId;
        this.origin = origin;
        return this;
    }
    public MessageChain at(Long target){
        this.type = Type.At;
        this.target = target;
        return this;
    }
    public MessageChain atAll(){
        this.type = Type.AtAll;
        return this;
    }
    public MessageChain face(int faceId){
        this.type = Type.Face;
        this.faceId = faceId;
        return this;
    }
    public MessageChain face(String name){
        this.type = Type.Face;
        this.name = name;
        return this;
    }
    public MessageChain plain(String text){
        this.type = Type.Plain;
        this.text = text;
        return this;
    }
    public MessageChain image(String imageId){
        this.type = Type.Image;
        this.imageId = imageId;
        return this;
    }
    public MessageChain imageByUrl(String url){
        this.type = Type.Image;
        this.url = url;
        return this;
    }
    public MessageChain imageByPath(String path){
        this.type = Type.Image;
        this.path = path;
        return this;
    }
    public MessageChain imageByBase64(String base64){
        this.type = Type.Image;
        this.base64 = base64;
        return this;
    }

    public MessageChain flashImage(String imageId){
        this.type = Type.FlashImage;
        this.imageId = imageId;
        return this;
    }
    public MessageChain flashImageByUrl(String url){
        this.type = Type.FlashImage;
        this.url = url;
        return this;
    }
    public MessageChain flashImageByPath(String path){
        this.type = Type.FlashImage;
        this.path = path;
        return this;
    }
    public MessageChain flashImageBase64(String base64){
        this.type = Type.FlashImage;
        this.base64 = base64;
        return this;
    }

    public MessageChain voice(String voiceId){
        this.type = Type.Voice;
        this.voiceId = voiceId;
        return this;
    }
    public MessageChain voiceByUrl(String url){
        this.type = Type.Voice;
        this.url = url;
        return this;
    }
    public MessageChain voiceByPath(String path){
        this.type = Type.Voice;
        this.path = path;
        return this;
    }
    public MessageChain voiceBase64(String base64){
        this.type = Type.Voice;
        this.base64 = base64;
        return this;
    }
    public MessageChain xml(String xml){
        this.type = Type.Xml;
        this.xml = xml;
        return this;
    }
    public MessageChain json(String json){
        this.type = Type.Json;
        this.json = json;
        return this;
    }
    public MessageChain app(String content){
        this.type = Type.App;
        this.content = content;
        return this;
    }
    public MessageChain poke(String name){
        this.type = Type.Poke;
        this.name = name;
        return this;
    }
    public MessageChain dice(int value){
        this.type = Type.Dice;
        this.value = value;
        return this;
    }
    /**
     * 目前商城表情仅支持接收和转发，不支持构造发送
     * */
    public MessageChain marketFace(int id,String name){
//        this.type = Type.Poke;
//        this.name = name;
        return null;
    }

    public MessageChain musicShare(String kind,String title,String summary,String jumpUrl,String pictureUrl,String musicUrl,String brief){
        this.type = Type.MusicShare;
        this.kind = kind;
        this.title = title;
        this.summary = summary;
        this.jumpUrl = jumpUrl;
        this.pictureUrl = pictureUrl;
        this.musicUrl = musicUrl;
        this.brief = brief;
        return this;
    }

    public MessageChain forwardMessage(List<MessageNode> nodeList){
        this.type = Type.ForwardMessage;
        this.nodeList = nodeList;
        return null;
    }
    public MessageChain file(String id,String name,Long size){
        this.type = Type.File;
        this.id = id;
        this.name=name;
        this.size = size;
        return null;
    }

    public MessageChain miraiCode(String code){
        this.type = Type.MiraiCode;
        this.code = code;
        return this;
    }


    public enum Type{
        Source,Quote,At,AtAll,Face,Plain,Image,FlashImage,Voice,Xml,Json,App,Poke,Dice,MarketFace,MusicShare,ForwardMessage,File,MiraiCode;
    }

}

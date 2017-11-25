package com.playlistapp.data.network.data.track;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import timber.log.Timber;

/**
 * {@link com.google.gson.JsonDeserializer} class for Tracks Feed class.
 */
public class TrackFeedDeserializer implements JsonDeserializer<TrackResponse> {

    @Override
    public TrackResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final JsonObject resultJsonObject = json.getAsJsonObject();
        Timber.d(":: resultJsonObject : " + resultJsonObject);

        TrackResponse response = new TrackResponse();
        Gson gson = new Gson();

//        List<TrackItem> trackItems = new ArrayList<>();
//        final JsonArray entryArray = resultJsonObject.getAsJsonArray("entry");
//        if (entryArray != null && entryArray.size() > 0) {
//            for (JsonElement entry :
//                    entryArray) {
//                Timber.d(":: element : " + entry);
//
//                JsonObject entryObject = entry.getAsJsonObject();
//
//                TrackItem item = new TrackItem();
//
//                // id
//                JsonElement idElement = entryObject.get("id");
//                LabelAttributes idLabel = gson.fromJson(idElement, LabelAttributes.class);
//                item.setId(idLabel.getAttributes().getId());
//
//                // title
//                JsonElement titleElement = entryObject.get("title");
//                LabelAttributes titleLabel = gson.fromJson(titleElement, LabelAttributes.class);
//                item.setTitle(titleLabel.getLabel());
//
//                // name
//                JsonElement nameElement = entryObject.get("im:name");
//                LabelAttributes nameLabel = gson.fromJson(nameElement, LabelAttributes.class);
//                item.setName(nameLabel.getLabel());
//
//                // price
//                JsonElement priceElement = entryObject.get("im:price");
//                LabelAttributes priceLabel = gson.fromJson(priceElement, LabelAttributes.class);
//                item.setPrice(priceLabel.getLabel());
//                item.setAmount(priceLabel.getAttributes().getAmount());
//
//                // rights
//                JsonElement rightsElement = entryObject.get("rights");
//                LabelAttributes rightsLabel = gson.fromJson(rightsElement, LabelAttributes.class);
//                item.setRights(rightsLabel.getLabel());
//
//                // artist
//                JsonElement artistElement = entryObject.get("im:artist");
//                LabelAttributes artistLabel = gson.fromJson(artistElement, LabelAttributes.class);
//                item.setArtist(artistLabel.getLabel());
//                item.setArtistLink(artistLabel.getAttributes().getAmount());
//
//                trackItems.add(item);
//            }
//        }
//        trackFeed.setTrackItems(trackItems);


        return response;
    }
}

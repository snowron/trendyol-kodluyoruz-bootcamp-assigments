package com.kodluyoruz.playlistapi.Repositories;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.kodluyoruz.playlistapi.Domain.Playlist;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

@Repository
public class PlaylistRepository {
    private final Cluster couchbaseCluster;
    private final Collection playlistsCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistsCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistsCollection = playlistsCollection;
    }

    public void insert(Playlist playlist) {
        playlistsCollection.insert(playlist.getId(), playlist);
    }

    public void remove(String id) {
        playlistsCollection.remove(id);
    }

    public void update(Playlist playlist) {
        playlistsCollection.replace(playlist.getId(), playlist);
    }

    public Playlist findById(String id) {
        GetResult getResult = playlistsCollection.get(id);
        Playlist playlist = getResult.contentAs(Playlist.class);
        return playlist;
    }

    public List<Playlist> findAllByUserId(String userId) {
        String statement = "SELECT id,name,description,followersCount,tracks,trackCount,userId FROM Playlist WHERE userId=$userId ";
        QueryResult query = couchbaseCluster.query(statement,
                queryOptions().parameters(JsonObject.create().put("userId", userId)));
        return query.rowsAs(Playlist.class);
    }

    public List<Playlist> findPlaylistWithSearch(String searchKeyword) {
        System.out.println(searchKeyword);
        String statement = "SELECT id,name,description,followersCount,tracks,trackCount,userId FROM Playlist WHERE ANY track IN Playlist.tracks SATISFIES track.name" +
                " LIKE $tname OR name LIKE $name end";
        QueryResult query = couchbaseCluster.query(statement,
                queryOptions().parameters(JsonObject.create().put("name", "%" + searchKeyword + "%")
                        .put("tname", "%" + searchKeyword + "%")));
        return query.rowsAs(Playlist.class);
    }
}

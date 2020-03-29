<template>
    <page-container id="map-page">
        <h1>Maps</h1>
        <div class="buttons">
            <!--button-fab type="info" v-bind:click="doShowMap" title="geolocalise ma poistion"
                        icon="fa-map-pin"></button-fab-->
        </div>
        <div v-if="showMap" class="grid">
            <div><label>Clique sur le point.</label></div>
        </div>
        <div v-if="showMap" class="grid">
            <l-map
                    style="height: 50vh; width: 100%"
                    :zoom="zoom"
                    :center="center"
            >
                <l-tile-layer :url="url"/>
                <l-marker :lat-lng="withPopup">
                    <l-popup>
                        <div>
                            Je suis une popup

                        </div>
                    </l-popup>
                </l-marker>
            </l-map>
        </div>
        <div v-if="geolocation.error" class="grid">
            <p>{{geolocation.msgError}}</p>
        </div>
        <div v-if="!showMap && geolocation.ok && !geolocation.error" class="grid">
            <gmap-map :center="geolocation.position" :zoom="25" class="maps">
                <gmap-marker :position="geolocation.position"></gmap-marker>
            </gmap-map>
        </div>
    </page-container>
</template>
<script>
    import Vue from 'vue';
    import { Icon, latLng } from 'leaflet';
    import { LMap, LMarker, LPopup, LTileLayer } from 'vue2-leaflet';
    import PageContainer from '../PageContainer';
    import 'leaflet/dist/leaflet.css';
    import ButtonFab from '../ButtonFab';

    delete Icon.Default.prototype._getIconUrl;
    Icon.Default.mergeOptions({
        iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
        iconUrl: require('leaflet/dist/images/marker-icon.png'),
        shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
    });

    export default {
        name: 'mapsPage',
        components: {
            PageContainer, ButtonFab,
            LMap,
            LTileLayer,
            LMarker,
            LPopup,
        },
        data() {
            return {
                showMap: true,
                center: { lat: 47.902964, lng: 1.909251 },
                zoom: 10,
                url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                withPopup: latLng(47.902964, 1.909251),
                geolocation: {
                    ok: false, position: {}
                },

                infoContent: '',
                infoWindowPos: {
                    lat: 0,
                    lng: 0
                },
                infoWinOpen: false,
                infoOptions: {
                    pixelOffset: {
                        width: 0,
                        height: -35
                    }
                },
            };
        },

        methods: {
            doShowMap() {
                const newMaps = !this.showMap;
                this.geolocation.error = false;
                this.geolocation.ok = false;
                // Geolocalise la position
                if (!newMaps) {
                    this.geolocation.ok = navigator.geolocation;
                    if (navigator.geolocation) {
                        navigator.geolocation.getCurrentPosition((position) => {
                                console.log(position)
                                const lat = position.coords.latitude;
                                const lng = position.coords.longitude;
                                this.geolocation.position = { lat, lng };
                                this.center = { lat, lng };
                                this.withPopup = LatLng(lat, lng);
                                this.showMap = newMaps;

                            },
                            (err) => {
                                console.error(err);
                                switch (err.code) {
                                    case err.PERMISSION_DENIED:
                                        this.geolocation.msgError = 'Vous avez refusé la fonction de géolocalisation.';
                                        break;
                                    case err.POSITION_UNAVAILABLE:
                                        this.geolocation.msgError = `la position n'est pas accessible.`;
                                        break;
                                    case err.TIMEOUT:
                                        this.geolocation.msgError = `Time out de la requête.`;
                                        break;
                                    default:
                                        this.geolocation.msgError = `une erreur inconnue s'est produite.`;
                                        break;
                                }
                                this.geolocation.error = true;
                            });
                    }
                } else {
                    this.showMap = newMaps;
                }
            },

        }
    }
</script>
<style>
    #map-page .buttons {
        display: flex;
        justify-content: center;
        margin: 1em;
    }

    #map-page .maps {
        min-height: 50vh
    }
</style>

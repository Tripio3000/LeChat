import {makeAutoObservable} from "mobx";

export default class MessageStore {
    constructor() {
        this._messages = []
        this._pictures = [
            {id: 'bcc476f7-df6f-42da-9598-b1143bc30c80', message_id: 'a4020e34-7e9e-4d5c-8895-1a5927f9e185', content_type: 'jpg', name: 'CatFile', filePath: '/Users/alex/Diploma/FileStorage/069/178/CatFile.jpg'}
        ]
        makeAutoObservable(this)
    }

    setMessages(messages) {
        this._messages = messages
    }

    get messages() {
        return this._messages
    }

    setPictures(pictures) {
        this._pictures = pictures
    }

    get pictures() {
        return this._pictures
    }
}
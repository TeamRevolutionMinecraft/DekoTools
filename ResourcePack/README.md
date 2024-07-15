# Texturepack

## Tools
Meine Empfehlung für die Texturepack erstellung
- [VSCode](https://code.visualstudio.com/) für die erstellung / Bearbeitung der Json datei
- [Paint.net](https://www.getpaint.net/) für die Texture bearbeitung

Beides ist Kostenlos

Für 3D models haben wir 2 Lizenzen von [CubicStudio](https://cubik.studio/)

## Wie erstelle ich eine Custom texture?

### Texture.json erstellen

Eine .json datei in dem ordner /assets/minecraft/models/item erstellen. Diese sieht wie folgt aus
```
{
	"parent": "item/handheld",
	"textures": {
		"layer0": "item/gold_nugget"
	},

	"overrides": [
		{"predicate": {"custom_model_data":1000000}, "model": "item/money/gold"}
	]
}
```

- parent ist die art von texture anzeige. Will sagen wo wird das Item dargestellt
- layer0 ist die Basis texture des items
- overrides ist weißt dem Item die CustomTextur zu. Sollte 8 stellig sein um komisches verhalten zu verhindern
    - model zeigt auf die [model.Json](#modeljson)

### Model.json

Diese Json datei sorgt für die Textur.
```
{
	"parent": "item/handheld",
	"textures": {
	  "layer0": "item/money/gold"
	}
}
```
- Die texture layer0 ist der Pfad zur texture

Im beispiel /assets/minecraft/textures/item/money/gold.png

Der dateityp muss in der json nicht angegeben werden.

## Wie teste ich die Textur

Mit dem befehl /give @p gold_nugget{CustomModelData:100000} 1 bekommst du das Item mit dem CustomModelData Value.

Auf Essentials serven kann es passieren das du /minecraft:give @p gold_nugget{CustomModelData:100000} 1 eingeben musst damit es richtig funktioniert

mit F3 + T lädst du dann das resource pack neu
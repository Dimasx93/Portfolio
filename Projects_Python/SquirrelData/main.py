import pandas as pd

data = pd.read_csv("2018_Central_Park_Squirrel_Census_-_Squirrel_Data.csv")
gray_squirrels = len(data[data["Primary Fur Color"] == "Gray"])
cinnamon_squirrels = len(data[data["Primary Fur Color"] == "Cinnamon"])
black_squirrels = len(data[data["Primary Fur Color"] == "Black"])

data_dict = {
    "Fur Color" : ["Gray", "Cinnamon", "Black"],
    "Count" : [gray_squirrels, cinnamon_squirrels, black_squirrels]
}

df = pd.DataFrame(data_dict)
df.to_csv("New_squirrel_count.csv")

print(df)
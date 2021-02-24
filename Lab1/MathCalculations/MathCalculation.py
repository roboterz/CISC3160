
#Data from Weather.com
weather = [[52,37], [46,30],[41,35],[51,40],[46,42],[49,29],[38,31],[47,37],[48,36],[46,37]]

#convert temperatures in degrees Fahrenheit to Celsius
def covertFtoC(e):
    return int((e-32)*5/9)


print('\tFahrenheit \t Celsius' )
print('\tHigh / Low \t High / Low' )

for i in range(len(weather)):
    print(f'Day {i+1}:\t{weather[i][0]} / {weather[i][1]} \t\t {covertFtoC(weather[i][0])} / {covertFtoC(weather[i][1])}')
const filterFields = document.getElementsByTagName("input");
const maxPriceElement = document.getElementById("maxPrice");
const minRateElement = document.getElementById("minRate");
Array.from(filterFields).forEach(function(field) {
    field.addEventListener('input', function(e) {
        filter(maxPriceElement.value, minRateElement.value)
    });
});

function filter(maxPrice, minRate) {
    let allHotelDivs = document.getElementsByClassName("hotel");
    Array.from(allHotelDivs).forEach(function(div) {
        div.style.removeProperty("display");
    });

    Array.from(allHotelDivs).forEach(function(div) {
        divMinPrice = Number(div.querySelector('.minPrice').textContent);
        divRate = div.querySelector('.rate').textContent;

        if (maxPrice !== "" && Number(maxPrice) < divMinPrice)
            div.style.display = 'none';
        else if (minRate !== "" && !isNaN(divRate) && Number(minRate) > Number(divRate))
            div.style.display = 'none';
    });
}


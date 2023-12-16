function initCanvas() {
    var canvas = document.getElementById('graph');
    var ctx = canvas.getContext('2d');
    var scale = 100;  // Radius / 2

    ctx.beginPath();
    ctx.moveTo(300, 0);
    ctx.lineTo(300, 600);
    ctx.moveTo(0, 300);
    ctx.lineTo(600, 300);
    ctx.strokeStyle = 'black';
    ctx.stroke();

    ctx.beginPath();
    ctx.arc(300, 300, scale * 2, Math.PI, Math.PI * 1.5, false);
    ctx.strokeStyle = 'blue';
    ctx.stroke();

    ctx.beginPath();
    ctx.rect(300, 300 - scale, scale * 2, scale);
    ctx.strokeStyle = 'green';
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(300 - scale * 2, 300);
    ctx.lineTo(300, 300);
    ctx.lineTo(300, 300 + scale * 2);
    ctx.closePath();
    ctx.strokeStyle = 'red';
    ctx.stroke();
}

function isInsideShapes(x, y, R) {
    let insideCircle = (x <= 0 && y >= 0) && (x * x + y * y <= R * R);
    let insideRectangle = (x >= 0 && x <= R) && (y <= R && y >= R / 2);
    let insideTriangle = (x <= 0 && y <= 0) && (y >= -x - R);
    return insideCircle || insideRectangle || insideTriangle;
}

function addPointCanvas(event, x, y, r) {
    var canvas = document.getElementById('graph');
    var context = canvas.getContext('2d');

    if (event === null) {
        x = x * (200 / r) + 300;
        y = 300 - y * (200 / r);

        context.fillStyle = '#f00';
        context.fillRect(x, y, 10, 10);
    } else {
        context.fillStyle = '#f00';
        context.fillRect(event.pageX - canvas.offsetLeft, event.pageY - canvas.offsetTop, 10, 10);
    }
}
